package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.*;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.babyfish.jimmer.sql.exception.SaveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;


/**
 * Recommended learning sequence: 5
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> [Current: ManyToManyTest] -> RecursiveTest -> TriggerTest
 */
public class ManyToManyTest extends AbstractMutationTest {

    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */

    @Test
    public void testInsertMiddleTableByShortAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "SQL in Action", 1, new BigDecimal(45)
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                1L, "Fabrice", "Marguerie", "M"
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                2L, "Steve", "Eichert", "M"
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                3L, "Jim", "Wooley", "M"
        );

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("SQL in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                            draft.addIntoAuthors(a -> a.setId(1L));
                            draft.addIntoAuthors(a -> a.setId(2L));
                            draft.addIntoAuthors(a -> a.setId(3L));
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) " +
                                "key(NAME, EDITION) values(?, ?, ?)",
                        "SQL in Action", 1, new BigDecimal(49)
                ),

                // Detach unnecessary associations based on `Book.authors`
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING " +
                                "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                        1L, Arrays.asList(1L, 2L, 3L)
                ),

                // Attach new associations based on `Book.authors`
                ExecutedStatement.batchOf(
                        "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                                "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                                "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                                "when not matched then " +
                                "insert(BOOK_ID, AUTHOR_ID) " +
                                "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                        Arrays.asList(1L, 1L),
                        Arrays.asList(1L, 2L),
                        Arrays.asList(1L, 3L)
                )
        );
        Assertions.assertEquals(4, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
        Assertions.assertEquals(3, result.getAffectedRowCount(BookProps.AUTHORS));
    }

    @Test
    public void  testDeleteMiddleTableByShortAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "LINQ in Action", 1, new BigDecimal(45)
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                1L, "Fabrice", "Brumm", "M"
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                2L, "Steve", "Eichert", "M"
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                3L, "Jim", "Wooley", "M"
        );
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 1L);
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 2L);
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 3L);

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("LINQ in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                            draft.addIntoAuthors(a -> a.setId(1L));
                            // Only `Author-1` is specified here,
                            // so old associations to `Author-2`
                            // and `Author-3` will be detached
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) key(NAME, EDITION) values(?, ?, ?)",
                        "LINQ in Action", 1, new BigDecimal(49)
                ),

                // Detach unnecessary associations base on `Book.authors`
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING " +
                                "where BOOK_ID = ? and AUTHOR_ID <> ?",
                        1L, 1L
                ),

                // Attach new associations base on `Book.authors`
                // Even if no data is actually modified, performing an upsert on the
                // underlying database(i.e: `merge` of `H2`) will still cause the
                // affected row count of the table to increase.
                ExecutedStatement.of(
                        "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                                "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                                "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                                "when not matched then " +
                                "insert(BOOK_ID, AUTHOR_ID) " +
                                "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                        1L, 1L
                )
        );

        Assertions.assertEquals(3, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
        Assertions.assertEquals(2, result.getAffectedRowCount(BookProps.AUTHORS));
    }

    @Test
    public void  testIllegalShortAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "LINQ in Action", 1, new BigDecimal(45)
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
                1L, "Fabrice", "Marguerie", "M"
        );

        SaveException ex = Assertions.assertThrows(SaveException.class, () -> {
            sql().save(
                    BookDraft.$.produce(draft -> {
                        draft.setName("LINQ in Action");
                        draft.setEdition(1);
                        draft.setPrice(new BigDecimal(49));
                        draft.addIntoAuthors(a -> a.setId(1L));
                        draft.addIntoAuthors(a -> a.setId(88888L));
                        draft.addIntoAuthors(a -> a.setId(99999L));
                    })
            );
        });

        Assertions.assertEquals(
                "Save error caused by the path: \"<root>.authors\": " +
                        "Cannot save the entity, the associated id of the reference property " +
                        "\"org.babyfish.jimmer.example.save.model.Book.authors\" is \"88888\" " +
                        "but there is no corresponding associated object in the database",
                ex.getMessage()
        );

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) key(NAME, EDITION) values(?, ?, ?)",
                        "LINQ in Action", 1, new BigDecimal(49)
                ),

                // Detach unnecessary associations based on `Book.authors`
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING " +
                                "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                        1L, Arrays.asList(1L, 88888L, 99999L)
                ),

                // Attach new associations based on `Book.authors`
                ExecutedStatement.batchOf(
                        "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                                "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                                "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                                "when not matched then " +
                                "insert(BOOK_ID, AUTHOR_ID) " +
                                "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                        Arrays.asList(1L, 1L),
                        Arrays.asList(1L, 88888L),
                        Arrays.asList(1L, 99999L)
                ),

                // Investigate why the database throws
                // error about constraint violation
                ExecutedStatement.of(
                        "select tb_1_.ID from AUTHOR tb_1_ " +
                                "where tb_1_.ID = ?",
                        88888L
                )
        );
    }

    @Test
    public void  testInsertMiddleTableByLongAssociation() {

        jdbc(
                "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
                1L, "LINQ in Action", 1, new BigDecimal(45)
        );

        SimpleSaveResult<Book> result = sql()
                .getEntities()
                .saveCommand(
                        BookDraft.$.produce(draft -> {
                            draft.setName("LINQ in Action");
                            draft.setEdition(1);
                            draft.setPrice(new BigDecimal(49));
                            draft.addIntoAuthors(author -> {
                                author.setFirstName("Fabrice");
                                author.setLastName("Marguerie");
                                author.setGender(Gender.MALE);
                            });
                            draft.addIntoAuthors(author -> {
                                author.setFirstName("Steve");
                                author.setLastName("Eichert");
                                author.setGender(Gender.MALE);
                            });
                            draft.addIntoAuthors(author -> {
                                author.setFirstName("Jim");
                                author.setLastName("Wooley");
                                author.setGender(Gender.MALE);
                            });
                        })
                )
                .execute();

        assertExecutedStatements(

                // Merge aggregate-root
                ExecutedStatement.of(
                        "merge into BOOK(NAME, EDITION, PRICE) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?)",
                        "LINQ in Action", 1, new BigDecimal(49)
                ),

                // Merge associated objects
                ExecutedStatement.batchOf(
                        "merge into AUTHOR(FIRST_NAME, LAST_NAME, GENDER) " +
                                "key(FIRST_NAME, LAST_NAME) " +
                                "values(?, ?, ?)",
                        Arrays.asList("Fabrice", "Marguerie", "M"),
                        Arrays.asList("Steve", "Eichert", "M"),
                        Arrays.asList("Jim", "Wooley", "M")
                ),

                // Detach unnecessary associations based on `Book.authors`
                ExecutedStatement.of(
                        "delete from BOOK_AUTHOR_MAPPING " +
                                "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                        1L, Arrays.asList(100L, 101L, 102L)
                ),

                // Attach new associations based on `Book.authors`
                ExecutedStatement.batchOf(
                        "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                                "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                                "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                                "when not matched then " +
                                "insert(BOOK_ID, AUTHOR_ID) " +
                                "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                        Arrays.asList(1L, 100L),
                        Arrays.asList(1L, 101L),
                        Arrays.asList(1L, 102L)
                )
        );

        Assertions.assertEquals(7, result.getTotalAffectedRowCount());
        Assertions.assertEquals(1, result.getAffectedRowCount(Book.class));
        Assertions.assertEquals(3, result.getAffectedRowCount(Author.class));
        Assertions.assertEquals(3, result.getAffectedRowCount(BookProps.AUTHORS));
    }
}