package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Recommended learning sequence: 6
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> [Current: BulkTest] -> RecursiveTest -> TriggerTest
 */
public class BulkTest extends AbstractMutationTest {

    @Test
    public void testBulkSave() {

        /**
         * 1. Old trees
         *
         * ----O'REILLY
         *
         * --+-MANNING
         *   |
         *   +-+-GraphQL in Action(edition 1)
         *   | |
         *   | \---Samer Buna
         *   |
         *   +-+-GraphQL in Action(edition 2)
         *   | |
         *   | \---Samer Buna
         *   |
         *   +-+-LINQ in Action(edition)
         *     |
         *     +---Fabrice Marguerie
         *     |
         *     \---Steve Eichert
         *
         * ----Jim Wooley (id = 4, Alone object)
         *
         * ----Vajo Lukic (id = 5, Alone object)
         */
        jdbc(
                "insert into book_store(id, name, website) values(?, ?, ?), (?, ?, ?)",
                1, "O'REILLY", "http://www.oreilly.com",
                2, "MANNING", "http://www.manning.com"
        );
        jdbc(
                "insert into book(id, name, edition, price, store_id) " +
                        "values(?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)",
                1, "GraphQL in Action", 1, new BigDecimal("26.99"), 2,
                2, "GraphQL in Action", 2, new BigDecimal("27.99"), 2,
                3, "LinQ in Action", 1, new BigDecimal("25.19"), 2
        );
        jdbc(
                "insert into author(id, first_name, last_name, gender) " +
                        "values(?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?)",
                1L, "Samer", "Buna", 'M',
                2L, "Fabrice", "Marguerie", 'M',
                3L, "Steve", "Eichert", 'M',
                4L, "Jim", "Wooley", 'M',
                5L, "Vajo", "Lukic", 'M'
        );
        jdbc(
                "insert into book_author_mapping(book_id, author_id)" +
                        "values(?, ?), (?, ?), " +
                        "(?, ?), (?, ?)",
                1L, 1L, 2L, 1L,
                3L, 2L, 3L, 3L
        );

        /**
         * 2. New trees
         */
        List<BookStore> stores = Arrays.asList(
                BookStoreDraft.$.produce(s -> {
                    s.setName("MANNING");
                    s.addIntoBooks(b -> {
                        b.setName("LINQ in Action");
                        b.setEdition(1);
                        b.setPrice(new BigDecimal("30.19"));
                        b.addIntoAuthors(a -> a.setId(3L));
                        b.addIntoAuthors(a -> a.setId(4L));
                    });
                }),
                BookStoreDraft.$.produce(s -> {
                    s.setName("AMAZON");
                    s.addIntoBooks(b -> {
                        b.setName("SQL Crash Course");
                        b.setEdition(1);
                        b.setPrice(new BigDecimal("23.99"));
                        b.addIntoAuthors(a -> a.setId(5L));
                    });
                    s.addIntoBooks(b -> {
                        b.setName("SQL Crash Course");
                        b.setEdition(2);
                        b.setPrice(new BigDecimal("24.99"));
                        b.addIntoAuthors(a -> a.setId(5L));
                    });
                })
        );

        /**
         * 3. Replace all trees
         */
        sql().saveEntities(stores);

        /**
         * 4. Validate DML
         *
         *    For each level
         *    -   Use database-level upsert
         *    -   Use Batch DML
         */
        assertExecutedStatements(

                ExecutedStatement.batchOf(
                        "merge into BOOK_STORE tb_1_ " +
                                "using(values(?)) tb_2_(NAME) " +
                                "--->on tb_1_.NAME = tb_2_.NAME " +
                                "when matched then " +
                                "--->update set /* fake update to return all ids */ NAME = tb_2_.NAME " +
                                "when not matched then " +
                                "--->insert(NAME) values(tb_2_.NAME)",
                        Collections.singletonList("MANNING"),
                        Collections.singletonList("AMAZON")
                ),

                ExecutedStatement.batchOf(
                        "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                                "key(NAME, EDITION) " +
                                "values(?, ?, ?, ?)",
                        Arrays.asList("LINQ in Action", 1, new BigDecimal("30.19"), 2L),
                        Arrays.asList("SQL Crash Course", 1, new BigDecimal("23.99"), 100L),
                        Arrays.asList("SQL Crash Course", 2, new BigDecimal("24.99"), 100L)
                ),

                ExecutedStatement.batchOf(
                        "delete from BOOK_AUTHOR_MAPPING " +
                                "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                        Arrays.asList(100L, Arrays.asList(3L, 4L)),
                        Arrays.asList(101L, Collections.singletonList(5L)),
                        Arrays.asList(102L, Collections.singletonList(5L))
                ),

                ExecutedStatement.batchOf(
                        "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                                "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                                "--->on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                                "when not matched then " +
                                "--->insert(BOOK_ID, AUTHOR_ID) " +
                                "--->values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                        Arrays.asList(100L, 3L),
                        Arrays.asList(100L, 4L),
                        Arrays.asList(101L, 5L),
                        Arrays.asList(102L, 5L)
                ),

                ExecutedStatement.batchOf(
                        "delete from BOOK_AUTHOR_MAPPING tb_1_ " +
                                "where exists (" +
                                "--->select * " +
                                "--->from BOOK tb_2_ " +
                                "--->where " +
                                "--->--->tb_1_.BOOK_ID = tb_2_.ID " +
                                "--->and " +
                                "--->--->STORE_ID = ? " +
                                "--->and " +
                                "--->--->not (ID = any(?)))",
                        Arrays.asList(2L, Collections.singletonList(100L)),
                        Arrays.asList(100L, Arrays.asList(101L, 102L))
                ),

                ExecutedStatement.batchOf(
                        "delete from BOOK where STORE_ID = ? and not (ID = any(?))",
                        Arrays.asList(2L, Collections.singletonList(100L)),
                        Arrays.asList(100L, Arrays.asList(101L, 102L))
                )
        );

        /**
         * 5. Refetch trees and assert them
         */
        BookStoreTable table = BookStoreTable.$;
        List<BookStore> refetchedStores = sql()
                .createQuery(table)
                .orderBy(table.id())
                .select(
                        table.fetch(
                                BookStoreFetcher.$
                                        .allScalarFields()
                                        .books(
                                                BookFetcher.$
                                                        .allScalarFields()
                                                        .authors(
                                                                AuthorFetcher.$
                                                                        .allScalarFields(),
                                                                cfg -> cfg.filter(args ->
                                                                        args.orderBy(args.getTable().id())
                                                                )
                                                        ),
                                                cfg -> cfg.filter(args ->
                                                        args.orderBy(args.getTable().id())
                                                )
                                        )
                        )
                )
                .execute();
        assertContent(
                "[" +
                        "--->{" +
                        "--->--->\"id\":1,\"name\":\"O'REILLY\",\"website\":\"http://www.oreilly.com\",\"books\":[]" +
                        "--->}, " +
                        "--->{" +
                        "--->--->\"id\":2," +
                        "--->--->\"name\":\"MANNING\"," +
                        "--->--->\"website\":\"http://www.manning.com\"," +
                        "--->--->\"books\":[" +
                        "--->--->--->{" +
                        "--->--->--->--->\"id\":100," +
                        "--->--->--->--->\"name\":\"LINQ in Action\"," +
                        "--->--->--->--->\"edition\":1," +
                        "--->--->--->--->\"price\":30.19," +
                        "--->--->--->--->\"authors\":[" +
                        "--->--->--->--->--->{\"id\":3,\"firstName\":\"Steve\",\"lastName\":\"Eichert\",\"gender\":\"MALE\"}," +
                        "--->--->--->--->--->{\"id\":4,\"firstName\":\"Jim\",\"lastName\":\"Wooley\",\"gender\":\"MALE\"}" +
                        "--->--->--->--->]" +
                        "--->--->--->}" +
                        "--->--->]" +
                        "--->}, " +
                        "--->{" +
                        "--->--->\"id\":100," +
                        "--->--->\"name\":\"AMAZON\"," +
                        "--->--->\"website\":null," +
                        "--->--->\"books\":[" +
                        "--->--->--->{" +
                        "--->--->--->--->\"id\":101," +
                        "--->--->--->--->\"name\":\"SQL Crash Course\"," +
                        "--->--->--->--->\"edition\":1," +
                        "--->--->--->--->\"price\":23.99," +
                        "--->--->--->--->\"authors\":[" +
                        "--->--->--->--->--->{\"id\":5,\"firstName\":\"Vajo\",\"lastName\":\"Lukic\",\"gender\":\"MALE\"}" +
                        "--->--->--->--->]" +
                        "--->--->--->}," +
                        "--->--->--->{" +
                        "--->--->--->--->\"id\":102," +
                        "--->--->--->--->\"name\":\"SQL Crash Course\"," +
                        "--->--->--->--->\"edition\":2," +
                        "--->--->--->--->\"price\":24.99," +
                        "--->--->--->--->\"authors\":[" +
                        "--->--->--->--->--->{\"id\":5,\"firstName\":\"Vajo\",\"lastName\":\"Lukic\",\"gender\":\"MALE\"}" +
                        "--->--->--->--->]" +
                        "--->--->--->}" +
                        "--->--->]" +
                        "--->}" +
                        "]",
                refetchedStores
        );
    }
}
