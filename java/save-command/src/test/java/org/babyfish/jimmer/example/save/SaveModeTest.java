package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.Book;
import org.babyfish.jimmer.example.save.model.BookDraft;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Recommended learning sequence: 1
 *
 *
 * [Current: SaveModeTest] -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> RecursiveTest -> TriggerTest
 */
public class SaveModeTest extends AbstractMutationTest {

    @Test
    public void testInsertOnly() {
        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("SQL in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                        })

                )
                .setMode(SaveMode.INSERT_ONLY)
                .execute();

        // `INSERT_ONLY` represents direct insertion regardless of whether the data exists
        assertExecutedStatements(
                ExecutedStatement.of(
                        "insert into BOOK(NAME, EDITION, PRICE) values(?, ?, ?)",
                        "SQL in Action",
                        1, new BigDecimal(49)
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
        Assertions.assertEquals(100L, result.getModifiedEntity().id());
    }

    @Test
    public void testUpdateOnlyById() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                10L, "SQL in Action", 1, new BigDecimal(45)
        );

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setId(10L);
                            draft.setName("SQL in Action");
                            draft.setEdition(2);
                            draft.setPrice(new BigDecimal(49));
                        })

                )
                .setMode(SaveMode.UPDATE_ONLY)
                .execute();

        // `UPDATE_ONLY` represents direct update regardless of whether the data exists
        assertExecutedStatements(
                ExecutedStatement.of(
                        "update BOOK set NAME = ?, EDITION = ?, PRICE = ? where ID = ?",
                        "SQL in Action", 2, new BigDecimal(49), 10L
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
    }

    @Test
    public void testUpdateExistingDataByKey() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "SQL in Action", 1, new BigDecimal(45)
        );

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("SQL in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                        })

                )
                .setMode(SaveMode.UPDATE_ONLY)
                .execute();

        assertExecutedStatements(

                //Although `UPDATE_ONLY` is specified, the id attribute of the object is missing
                // so that it will still result in a key-based query.
                ExecutedStatement.of(
                        "update BOOK set PRICE = ? where NAME = ? and EDITION = ?",
                        new BigDecimal(49), "SQL in Action", 1
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1L, result.getModifiedEntity().id());
    }

    @Test
    public void testUpdateNonExistingDataByKey() {

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("SQL in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                        })

                )
                .setMode(SaveMode.UPDATE_ONLY)
                .execute();

        assertExecutedStatements(

                //Although `UPDATE_ONLY` is specified, the id attribute of the object is missing
                // so that it will still result in a key-based query.
                ExecutedStatement.of(
                        "update BOOK set PRICE = ? " +
                                "where NAME = ? and EDITION = ?",
                        new BigDecimal(49), "SQL in Action", 1
                ) // No data can be selected, do nothing(affected row count is 0)
        );

        // Nothing updated
        Assertions.assertEquals(0, result.getTotalAffectedRowCount());
    }

    @Test
    public void testUpsertExistingDataById() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "SQL in Action", 1, new BigDecimal(45)
        );

        SimpleSaveResult<Book> result = sql().save(
                BookDraft.$.produce(draft -> {
                    draft.setId(1L);
                    draft.setName("PL/SQL in Action");
                    draft.setEdition(2);
                })
        );

        assertExecutedStatements(

                // Query whether the data exists by id
                ExecutedStatement.of(
                        "merge into BOOK(ID, NAME, EDITION) key(ID) values(?, ?, ?)",
                        1L, "PL/SQL in Action", 2
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
    }

    @Test
    public void testUpsertExistingDataByKey() {
        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                10L, "SQL in Action", 1, new BigDecimal(45)
        );
        SimpleSaveResult<Book> result = sql().save(
                BookDraft.$.produce(draft -> {
                    draft.setName("SQL in Action");
                    draft.setEdition(1);
                    draft.setPrice(new BigDecimal(49));
                })
        );

        assertExecutedStatements(

                // Query whether the data exists by key
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49)
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
    }

    @Test
    public void testUpsertNonExistingDataById() {
        SimpleSaveResult<Book> result = sql().save(
                BookDraft.$.produce(draft -> {
                    draft.setId(10L);
                    draft.setName("SQL in Action");
                    draft.setEdition(2);
                    draft.setPrice(new BigDecimal(49));
                })
        );

        assertExecutedStatements(

                // Query whether the data exists by id
                ExecutedStatement.of(
                        "merge into BOOK(ID, NAME, EDITION, PRICE) " +
                                "key(ID) " +
                                "values(?, ?, ?, ?)",
                        10L, "SQL in Action", 2, new BigDecimal(49)
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
        Assertions.assertEquals(10L, result.getModifiedEntity().id());
    }

    @Test
    public void testUpsertNonExistingDataByKey() {
        SimpleSaveResult<Book> result = sql().save(
                BookDraft.$.produce(draft -> {
                    draft.setName("SQL in Action");
                    draft.setEdition(1);
                    draft.setPrice(new BigDecimal(49));
                })
        );

        assertExecutedStatements(

                // Query whether the data exists by key
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49)
                )
        );

        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
        Assertions.assertEquals(100L, result.getModifiedEntity().id());
    }
}