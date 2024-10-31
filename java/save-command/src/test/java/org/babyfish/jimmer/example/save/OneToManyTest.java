package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.Book;
import org.babyfish.jimmer.example.save.model.BookProps;
import org.babyfish.jimmer.example.save.model.BookStore;
import org.babyfish.jimmer.example.save.model.BookStoreDraft;
import org.babyfish.jimmer.sql.DissociateAction;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.babyfish.jimmer.sql.exception.SaveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * Recommended learning sequence: 4
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * [current: OneToManyTest] -> ManyToManyTest -> RecursiveTest -> TriggerTest
 */
public class OneToManyTest extends AbstractMutationTest {

    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */

    @Test
    public void  testAttachChildByShortAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L,
                "SQL in Action",
                1,
                new BigDecimal(45)
        );

        SimpleSaveResult<BookStore> result = sql().save(
                BookStoreDraft.$.produce(draft -> {
                    draft.setName("MANNING");
                    draft.addIntoBooks(book -> book.setId(1L));
                })
        );

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merged aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(ID, STORE_ID) " +
                                "key(ID) values(?, ?)",
                        1L, 100L
                ),

                // Before detach unnecessary books, the associations
                // based on `Book.authors` need to be detached first.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                                "where exists (" +
                                "--->select * " +
                                "--->from BOOK tb_2_ " +
                                "--->where " +
                                "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                                "--->and " +
                                "--->--->STORE_ID = ? " +
                                "--->and " +
                                "--->--->not (ID = any(?))" +
                                ")",
                        100L, Arrays.asList(1L)
                ),

                // Detach unnecessary books.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        100L, Collections.singletonList(1L)
                )
        );

        Assertions.assertEquals(2, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testAttachChildByAssociationBasedOnKey() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1,
                "SQL in Action",
                1,
                new BigDecimal(49)
        );

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                2,
                "Redis in Action",
                1,
                new BigDecimal(39)
        );

        SimpleSaveResult<BookStore> result = sql().save(
                BookStoreDraft.$.produce(draft -> {
                    draft.setName("MANNING");
                    draft.addIntoBooks(book -> {
                        book.setName("SQL in Action");
                        book.setEdition(1);
                    });
                    draft.addIntoBooks(book -> {
                        book.setName("Redis in Action");
                        book.setEdition(1);
                    });
                })
        );

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merge associated objects
                ExecutedStatement.batchOf(
                        "merge into BOOK(NAME, EDITION, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?)",
                        Arrays.asList("SQL in Action", 1, 100L),
                        Arrays.asList("Redis in Action", 1, 100L)
                ),

                // Before detach unnecessary books, the associations
                // based on `Book.authors` need to be detached first.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                        "where exists (" +
                        "--->select * " +
                        "--->from BOOK tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                        "--->and " +
                        "--->--->STORE_ID = ? " +
                        "--->and " +
                        "--->--->not (ID = any(?))" +
                        ")",
                        100L, Arrays.asList(1L, 2L)
                ),

                // Detach unnecessary books.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        100L, Arrays.asList(1L, 2L)
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testUpdateWithAssociation() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                1,
                "SQL in Action",
                1,
                new BigDecimal(45),
                1L
        );
        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                2,
                "Redis in Action",
                1,
                new BigDecimal(35),
                1L
        );

        SimpleSaveResult<BookStore> result = sql().save(
                BookStoreDraft.$.produce(draft -> {
                    draft.setName("MANNING");
                    draft.addIntoBooks(book -> {
                        book.setName("SQL in Action");
                        book.setEdition(1);
                        book.setPrice(new BigDecimal(49));
                    });
                    draft.addIntoBooks(book -> {
                        book.setName("Redis in Action");
                        book.setEdition(1);
                        book.setPrice(new BigDecimal(39));
                    });
                })
        );

        assertExecutedStatements(

                // Merge aggregate-root
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merge associated objects
                ExecutedStatement.batchOf(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        Arrays.asList("SQL in Action", 1, new BigDecimal(49), 1L),
                        Arrays.asList("Redis in Action", 1, new BigDecimal(39), 1L)
                ),

                // Before detach unnecessary books, the associations
                // based on `Book.authors` need to be detached first.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                        "where exists (" +
                        "--->select * " +
                        "--->from BOOK tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                        "--->and " +
                        "--->--->STORE_ID = ? " +
                        "--->and " +
                        "--->--->not (ID = any(?))" +
                        ")",
                        1L, Arrays.asList(1L, 2L)
                ),

                // Detach unnecessary books.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        1L, Arrays.asList(1L, 2L)
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testAttachChild() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");

        SimpleSaveResult<BookStore> result = sql().save(
                BookStoreDraft.$.produce(draft -> {
                    draft.setName("MANNING");
                    draft.addIntoBooks(book -> {
                        book.setName("SQL in Action");
                        book.setEdition(1);
                        book.setPrice(new BigDecimal(49));
                    });
                    draft.addIntoBooks(book -> {
                        book.setName("Redis in Action");
                        book.setEdition(1);
                        book.setPrice(new BigDecimal(39));
                    });
                })
        );

        assertExecutedStatements(

                // Merge aggregate-root
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merge associated objects
                ExecutedStatement.batchOf(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        Arrays.asList("SQL in Action", 1, new BigDecimal(49), 1L),
                        Arrays.asList("Redis in Action", 1, new BigDecimal(39), 1L)
                ),

                // Before detach unnecessary books, the associations
                // based on `Book.authors` need to be detached first.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                        "where exists (" +
                        "--->select * " +
                        "--->from BOOK tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                        "--->and " +
                        "--->--->STORE_ID = ? " +
                        "--->and " +
                        "--->--->not (ID = any(?))" +
                        ")",
                        1L, Arrays.asList(100L, 101L)
                ),

                // Detach unnecessary books.
                // However, nothing will be deleted in this case
                ExecutedStatement.of(
                        "delete from BOOK " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        1L, Arrays.asList(100L, 101L)
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testDetachChildFailed() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L,
                "SQL in Action",
                1,
                new BigDecimal(45)
        );

        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                21,
                "GraphQL in Action",
                1,
                new BigDecimal(39),
                1L
        );

        SaveException ex = Assertions.assertThrows(SaveException.class, () -> {
            sql()
                    .getEntities()
                    .saveCommand(
                            BookStoreDraft.$.produce(draft -> {
                                draft.setName("MANNING");
                                draft.addIntoBooks(book -> {book.setId(1L);});
                            })
                    )
                    .setDissociateAction(BookProps.STORE, DissociateAction.NONE)
                    .execute();
        });

        Assertions.assertEquals(
                "Save error caused by the path: \"<root>.books\": " +
                        "Cannot dissociate child objects because the " +
                        "dissociation action of the many-to-one property " +
                        "\"org.babyfish.jimmer.example.save.model.Book.store\" " +
                        "is not configured as \"set null\" or \"cascade\". " +
                        "There are two ways to resolve this issue: " +
                        "Decorate the many-to-one property " +
                        "\"org.babyfish.jimmer.example.save.model.Book.store\" " +
                        "by @org.babyfish.jimmer.sql.OnDissociate whose argument is " +
                        "`DissociateAction.SET_NULL` or `DissociateAction.DELETE`, " +
                        "or use save command's runtime configuration to override it",
                ex.getMessage()
        );

        assertExecutedStatements(

                // Merge aggregate-root by key
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merge associated objects
                ExecutedStatement.of(
                        "merge into BOOK(ID, STORE_ID) key(ID) values(?, ?)",
                        1L, 1L
                ),

                // The dissociate action of `Book.store` is `NONE`,
                // In the current global configuration, `NONE` is
                // treated as `CHECKING`. That means detaching old
                // child objects is not supported. Jimmer uses
                // select statement to check whether there are some
                // child objects need to be detached(unsupported),
                // if yes, then throws exception.
                ExecutedStatement.of(
                        "select tb_1_.ID from BOOK tb_1_ " +
                        "where " +
                        "--->tb_1_.STORE_ID = ? " +
                        "and " +
                        "--->tb_1_.ID <> ? " +
                        "limit ?",
                        1L, 1L, 1
                )
        );
    }

    @Test
    public void  testDetachChildByClearingForeignKey() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                10L,
                "SQL in Action",
                1,
                new BigDecimal(45)
        );
        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                1L,
                "GraphQL in Action",
                1,
                new BigDecimal(49),
                1L
        );
        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                2L,
                "Redis in Action",
                1,
                new BigDecimal(39),
                1L
        );

        SimpleSaveResult<BookStore> result = sql()
                .getEntities()
                .saveCommand(
                        BookStoreDraft.$.produce(draft -> {
                            draft.setName("MANNING");
                            draft.addIntoBooks(book -> {
                                book.setId(1L);
                            });
                        })
                )
                .setDissociateAction(BookProps.STORE, DissociateAction.SET_NULL)
                .execute();

        assertExecutedStatements(

                // Merge aggregate-root
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Merge associated objects
                ExecutedStatement.of(
                        "merge into BOOK(ID, STORE_ID) key(ID) values(?, ?)",
                        1L, 1L
                ),

                // Detached the unnecessary associated object(s)
                // in this example, detaching behavior is
                // configured as `SET_NULL`, here, the foreign
                // key of unnecessary associated objects will
                // be set to null
                ExecutedStatement.of(
                        "update BOOK set STORE_ID = null " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        1L, Collections.singletonList(1L)
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testDetachChildByDeletingChild() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L,
                "SQL in Action",
                1,
                new BigDecimal(45)
        );
        jdbc(
                "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
                2L,
                "GraphQL in Action",
                1,
                new BigDecimal(39),
                1L
        );

        SimpleSaveResult<BookStore> result = sql()
                .getEntities()
                .saveCommand(
                        BookStoreDraft.$.produce(draft -> {
                            draft.setName("MANNING");
                            draft.addIntoBooks(book -> {
                                book.setId(1L);
                            });
                        })
                )
                .setDissociateAction(BookProps.STORE, DissociateAction.DELETE)
                .execute();

        assertExecutedStatements(

                // Merge aggregate-root
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) key(NAME) values(?)",
                        "MANNING"
                ),

                // Marge associated objects
                ExecutedStatement.of(
                        "merge into BOOK(ID, STORE_ID) key(ID) values(?, ?)",
                        1L, 1L
                ),

                // `book{id:2}` will be detached, before doing that,
                // its associations bases on `Book.authors` must be
                // detached at first.
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                        "where exists (" +
                        "--->select * " +
                        "--->from BOOK tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                        "--->and STORE_ID = ? " +
                        "--->and " +
                        "--->--->not (ID = any(?))" +
                        ")",
                        1L, Collections.singletonList(1L)
                ),

                // Now, `book-20` can be deleted safely
                ExecutedStatement.of(
                        "delete from BOOK " +
                                "where STORE_ID = ? and not (ID = any(?))",
                        1L, Collections.singletonList(1L)
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(Book.class));
    }
}