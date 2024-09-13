package org.babyfish.jimmer.example.save.common;

import org.babyfish.jimmer.sql.runtime.DbLiteral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ExecutedStatement {

    private final String sql;

    private final List<List<Object>> variableLists;

    private ExecutedStatement(String sql, List<List<Object>> variableLists) {
        this.sql = sql;
        this.variableLists = variableLists;
    }

    public static ExecutedStatement of(String sql, Object ... variables) {
        List<Object> list = new ArrayList<>(variables.length);
        for (Object variable : variables) {
            list.add(variable instanceof DbLiteral.DbNull ? null : variable);
        }
        return new ExecutedStatement(
                simpleSql(sql),
                Collections.singletonList(
                        Collections.unmodifiableList(list)
                )
        );
    }

    @SafeVarargs
    public static ExecutedStatement batchOf(
            String sql,
            List<Object> ...variableMatrix
    ) {
        List<List<Object>> lists = new ArrayList<>(variableMatrix.length);
        for (List<Object> variables : variableMatrix) {
            List<Object> list = new ArrayList<>(variables.size());
            for (Object variable : variables) {
                list.add(variable instanceof DbLiteral.DbNull ? null : variable);
            }
            lists.add(Collections.unmodifiableList(list));
        }
        return new ExecutedStatement(
                simpleSql(sql),
                Collections.unmodifiableList(lists)
        );
    }

    public String getSql() {
        return sql;
    }

    public List<List<Object>> getVariableLists() {
        return variableLists;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sql, variableLists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutedStatement that = (ExecutedStatement) o;
        return sql.equals(that.sql) && variableLists.equals(that.variableLists);
    }

    @Override
    public String toString() {
        return "ExecutedStatement{" +
                "sql='" + sql + '\'' +
                ", variableLists=" + variableLists +
                '}';
    }

    private static String simpleSql(String sql) {
        return sql
                .replace("--->", "")
                .replace("\n", "")
                .replace("\r", "");

    }
}
