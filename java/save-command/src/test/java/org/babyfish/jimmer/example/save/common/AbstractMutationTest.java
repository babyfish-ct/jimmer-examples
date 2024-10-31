package org.babyfish.jimmer.example.save.common;

import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.dialect.H2Dialect;
import org.babyfish.jimmer.sql.runtime.*;
import org.h2.Driver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.BiFunction;

public abstract class AbstractMutationTest {

    private Connection connection;

    private JSqlClient sqlClient;

    private List<ExecutedStatement> executedStatements;

    @BeforeEach
    public void beforeTest() throws SQLException {
        connection = createConnection();
        createDatabase(connection);
        executedStatements = new ArrayList<>();
        JSqlClient.Builder builder = JSqlClient
                .newBuilder()
                .setDialect(new H2Dialect())
                .setTargetTransferable(true)
                .setExecutor(new RecordSqlExecutor())
                .setConnectionManager(ConnectionManager.singleConnectionManager(connection));
        customize(builder);
        sqlClient = builder.build();
    }

    @AfterEach
    public void afterTest() throws SQLException {
        sqlClient = null;
        Connection con = connection;
        if (con != null) {
            connection = null;
            con.close();
        }
    }

    protected JSqlClient sql() {
        return sqlClient;
    }

    protected void jdbc(String sql, Object ... args) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            statement.execute();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    protected void assertExecutedStatements(ExecutedStatement... executedStatements) {
        int count = Math.min(this.executedStatements.size(), executedStatements.length);
        for (int i = 0; i < count; i++) {
            Assertions.assertEquals(
                    executedStatements[i].getSql(),
                    this.executedStatements.get(i).getSql(),
                    "Failed to assert sql of statements[" + i + "]"
            );
            Assertions.assertEquals(
                    executedStatements[i].getVariableLists().size(),
                    this.executedStatements.get(i).getVariableLists().size(),
                    "Failed to assert batch row count of statements[" + i + "]"
            );
            Assertions.assertEquals(
                    executedStatements[i].getSql(),
                    this.executedStatements.get(i).getSql(),
                    "Failed to assert sql of statements[" + i + "]"
            );
            for (int ii = 0; ii < executedStatements[i].getVariableLists().size(); ii++) {
                Assertions.assertEquals(
                        executedStatements[i].getVariableLists().get(ii),
                        this.executedStatements.get(i).getVariableLists().get(ii),
                        "Failed to assert variables of statements[" + i + "][" + ii + "]"
                );
            }
        }
        Assertions.assertEquals(
                executedStatements.length,
                this.executedStatements.size(),
                "Expected " +
                        executedStatements.length +
                        " statements, but " +
                        this.executedStatements.size() +
                        " statements"
        );
    }

    // Can be overridden
    protected void customize(JSqlClient.Builder builder) {}

    private static Connection createConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("database_to_upper", "true");
        return new Driver().connect(
                "jdbc:h2:mem:save-cmd-example",
                properties
        );
    }

    private void createDatabase(Connection con) {
        InputStream stream = AbstractMutationTest.class.getClassLoader().getResourceAsStream("unit-test.sql");
        if (stream == null) {
            Assertions.fail("Failed to initialize database, cannot load 'database.sql'");
        }
        try (Reader reader = new InputStreamReader(stream)) {
            StringBuilder builder = new StringBuilder();
            char[] buf = new char[1024];
            int len;
            while ((len = reader.read(buf)) != -1) {
                builder.append(buf, 0, len);
            }
            con.createStatement().execute(builder.toString());
        } catch (IOException | SQLException ex) {
            Assertions.fail("Failed to initialize database", ex);
        }
    }

    private class RecordSqlExecutor implements Executor {

        @Override
        public <R> R execute(Args<R> args) {
            executedStatements.add(
                    ExecutedStatement.of(
                            args.sql,
                            args.variables.toArray(new Object[0])
                    )
            );
            return DefaultExecutor.INSTANCE.execute(args);
        }

        public Executor.BatchContext executeBatch(
                Connection con,
                String sql,
                @Nullable ImmutableProp generatedIdProp,
                @NotNull ExecutionPurpose purpose,
                @NotNull JSqlClientImplementor sqlClient
        ) {
            BatchContext raw = DefaultExecutor.INSTANCE.executeBatch(
                    con,
                    sql,
                    generatedIdProp,
                    purpose,
                    sqlClient
            );
            return new BatchContextWrapper(raw, sql, executedStatements);
        }
    }

    private static class BatchContextWrapper implements Executor.BatchContext {

        private final Executor.BatchContext raw;

        private final String sql;

        private final List<ExecutedStatement> executedStatements;

        private final List<List<Object>> variableLists = new ArrayList<>();

        BatchContextWrapper(
                Executor.BatchContext raw,
                String sql,
                List<ExecutedStatement> executedStatements
        ) {
            this.raw = raw;
            this.sql = sql;
            this.executedStatements = executedStatements;
        }

        @Override
        public JSqlClientImplementor sqlClient() {
            return raw.sqlClient();
        }

        @Override
        public String sql() {
            return raw.sql();
        }

        @Override
        public ExecutionPurpose purpose() {
            return raw.purpose();
        }

        @Override
        public ExecutorContext ctx() {
            return raw.ctx();
        }

        @Override
        public void add(List<Object> variables) {
            variableLists.add(variables);
            raw.add(variables);
        }

        @SuppressWarnings("unchecked")
        @Override
        public int[] execute(
                BiFunction<SQLException, Executor.BatchContext, Exception> exceptionTranslator
        ) {
            executedStatements.add(
                    ExecutedStatement.batchOf(
                            sql,
                            variableLists.toArray((List<Object>[])new List[0])
                    )
            );
            return raw.execute(exceptionTranslator);
        }

        @Override
        public Object[] generatedIds() {
            return raw.generatedIds();
        }

        @Override
        public void addExecutedListener(Runnable listener) {
            raw.addExecutedListener(listener);
        }

        @Override
        public void close() {
            raw.close();
        }
    }
}
