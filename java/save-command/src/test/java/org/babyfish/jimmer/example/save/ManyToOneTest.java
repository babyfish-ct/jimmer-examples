package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.Book;
import org.babyfish.jimmer.example.save.model.BookDraft;
import org.babyfish.jimmer.example.save.model.BookProps;
import org.babyfish.jimmer.example.save.model.BookStore;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.babyfish.jimmer.sql.exception.SaveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Recommended learning sequence: 3
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> [current: ManyToOneTest] ->
 * OneToManyTest -> ManyToManyTest -> RecursiveTest -> TriggerTest
 */
public class ManyToOneTest extends AbstractMutationTest {

    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */

    @Test
    public void  testShortAssociation() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
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
                            draft.setStoreId(1L);
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge the associated object.
                //
                // Associated object is parent object but aggregate-root is child object,
                // so it is handled before aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49), 1L
                )
        );
        Assertions.assertEquals(1, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testIllegalShortAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                10L, "SQL in Action", 1, new BigDecimal(45)
        );

        SaveException ex = Assertions.assertThrows(SaveException.class, () -> {
            sql()
                    .getEntities()
                    .saveCommand(
                            BookDraft.$.produce(draft -> {
                                draft.setName("GraphQL in Action");
                                draft.setEdition(1);
                                draft.setPrice(new BigDecimal(49));
                                draft.setStoreId(99999L);
                            })
                    )
                    .execute();
        });

        Assertions.assertEquals(
                "Save error caused by the path: \"<root>.store\": " +
                        "Cannot save the entity, the associated id of the reference property " +
                        "\"org.babyfish.jimmer.example.save.model.Book.store\" is \"99999\" " +
                        "but there is no corresponding associated object in the database",
                ex.getMessage()
        );

        assertExecutedStatements(

                // Merge the associated object.
                //
                // Associated object is parent object but aggregate-root is child object,
                // so it is handled before aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        "GraphQL in Action", 1, new BigDecimal(49), 99999L
                ),

                // Investigate why the database throws
                // error about constraint violation
                ExecutedStatement.of(
                        "select tb_1_.ID " +
                                "from BOOK_STORE tb_1_ " +
                                "where tb_1_.ID = ?",
                        99999L
                )
        );
    }

    @Test
    public void  testAssociationByKey() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "SQL in Action", 1, new BigDecimal(45)
        );

        SimpleSaveResult<Book> result = sql().save(
                BookDraft.$.produce(draft -> {
                    draft.setName("SQL in Action");
                    draft.setEdition(1);
                    draft.setPrice(new BigDecimal(49));
                    draft.applyStore(store -> {
                        store.setName("MANNING");
                    });
                })
        );

        assertExecutedStatements(

                // Merge the associated object.
                //
                // Associated object is parent object but aggregate-root is child object,
                // so it is handled before aggregate-root.
                //
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME) " +
                                "key(NAME) " +
                                "values(?)",
                        "MANNING"
                ),

                // Merge aggregation-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49), 1L
                )
        );

        Assertions.assertEquals(2, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testAssociationByExistingParent() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING");
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
                            draft.applyStore(store -> {
                                store.setName("MANNING");
                                store.setWebsite("https://www.manning.com");
                            });
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge the associated object.
                //
                // Associated object is parent object but aggregate-root is child object,
                // so it is handled before aggregate-root.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME, WEBSITE) " +
                                "key(NAME) " +
                                "values(?, ?)",
                        "MANNING", "https://www.manning.com"
                ),

                // Merge aggregation-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49), 1L
                )
        );

        Assertions.assertEquals(2, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
    }

    @Test
    public void  testAttachParent() {

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
                            draft.applyStore(store -> {
                                store.setName("TURING");
                                store.setWebsite("https://www.turing.com");
                            });
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge the associated object.
                //
                // Associated object is parent object but aggregate-root is child object,
                // so it is handled before aggregate-root.
                ExecutedStatement.of(
                        "merge into BOOK_STORE(NAME, WEBSITE) " +
                                "key(NAME) " +
                                "values(?, ?)",
                        "TURING", "https://www.turing.com"
                ),

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49), 100L
                )
        );
        Assertions.assertEquals(2, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(BookStore.class));
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
    }
}