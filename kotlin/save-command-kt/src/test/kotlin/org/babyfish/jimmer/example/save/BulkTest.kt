package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.awt.print.Book
import java.math.BigDecimal

/**
 * Recommended learning sequence: 6
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> [current: BulkTest] -> BulkTest -> RecursiveTest -> TriggerTest
 */
class BulkTest : AbstractMutationTest() {

    @Test
    fun testBulkSave() {

        /**
         * Old tree
         *
         * +---O'REILLY
         * |
         * +-+-MANNING
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
         * ----Vajo Lukic (id = 5, Alone object)
         */
        jdbc(
            "insert into book_store(id, name, website) values(?, ?, ?), (?, ?, ?)",
            1, "O'REILLY", "http://www.oreilly.com",
            2, "MANNING", "http://www.manning.com"
        )
        jdbc(
            "insert into book(id, name, edition, price, store_id) " +
                "values(?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)",
            1, "GraphQL in Action", 1, BigDecimal("26.99"), 2,
            2, "GraphQL in Action", 2, BigDecimal("27.99"), 2,
            3, "LinQ in Action", 1, BigDecimal("25.19"), 2
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) " +
                "values(?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?)",
            1L, "Samer", "Buna", 'M',
            2L, "Fabrice", "Marguerie", 'M',
            3L, "Steve", "Eichert", 'M',
            4L, "Jim", "Wooley", 'M',
            5L, "Vajo", "Lukic", 'M'
        )
        jdbc(
            "insert into book_author_mapping(book_id, author_id)" +
                "values(?, ?), (?, ?), " +
                "(?, ?), (?, ?)",
            1L, 1L, 2L, 1L,
            3L, 2L, 3L, 3L
        )

        val stores = listOf(
            BookStore {
                name = "MANNING"
                books().addBy {
                    name = "LINQ in Action"
                    edition = 1
                    price = BigDecimal("30.19")
                    authors().addBy {
                        id = 3L
                    }
                    authors().addBy {
                        id = 4L
                    }
                }
            },
            BookStore {
                name = "AMAZON"
                books().addBy {
                    name = "SQL Crash Course"
                    edition = 1
                    price = BigDecimal("23.99")
                    authors().addBy {
                        id = 5L
                    }
                }
                books().addBy {
                    name = "SQL Crash Course"
                    edition = 2
                    price = BigDecimal("24.99")
                    authors().addBy {
                        id = 5L
                    }
                }
            }
        )

        sql.saveEntities(stores)

        assertExecutedStatements(

            ExecutedStatement.batchOf(
                """merge into BOOK_STORE(NAME) 
                    |key(NAME) 
                    |values(?)""".trimMargin(),
                listOf("MANNING"),
                listOf("AMAZON"),
            ),

            ExecutedStatement.batchOf(
                """merge into BOOK(NAME, EDITION, PRICE, STORE_ID) 
                    |key(NAME, EDITION) v
                    |alues(?, ?, ?, ?)""".trimMargin(),
                listOf("LINQ in Action", 1, BigDecimal("30.19"), 2L),
                listOf("SQL Crash Course", 1, BigDecimal("23.99"), 100L),
                listOf("SQL Crash Course", 2, BigDecimal("24.99"), 100L)
            ),

            ExecutedStatement.batchOf(
                """delete from BOOK_AUTHOR_MAPPING 
                    |where BOOK_ID = ? and not (AUTHOR_ID = any(?))""".trimMargin(),
                listOf(100L, listOf(3L, 4L)),
                listOf(101L, listOf(5L)),
                listOf(102L, listOf(5L))
            ),

            ExecutedStatement.batchOf(
                """merge into BOOK_AUTHOR_MAPPING tb_1_ 
                    |using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) 
                    |--->on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID 
                    |when not matched then 
                    |--->insert(BOOK_ID, AUTHOR_ID) 
                    |--->values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)""".trimMargin(),
                listOf(100L, 3L),
                listOf(100L, 4L),
                listOf(101L, 5L),
                listOf(102L, 5L),
            ),

            ExecutedStatement.batchOf(
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and 
                    |--->--->STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?)))""".trimMargin(),
                listOf(2L, listOf(100L)),
                listOf(100L, listOf(101L, 102L))
            ),

            ExecutedStatement.batchOf(
                """delete from BOOK where STORE_ID = ? and not (ID = any(?))""",
                listOf(2L, listOf(100L)),
                listOf(100L, listOf(101L, 102L))
            )
        )

        val refetchedStores = sql.executeQuery(BookStore::class) {
            select(
                table.fetchBy {
                    allScalarFields()
                    books {
                        allScalarFields()
                        authors {
                            allScalarFields()
                        }
                    }
                }
            )
        }
        assertContent(
            """[
                |--->{
                |--->--->"id":1,"name":"O'REILLY","website":"http://www.oreilly.com","books":[]
                |--->}, 
                |--->{
                |--->--->"id":2,
                |--->--->"name":"MANNING",
                |--->--->"website":"http://www.manning.com",
                |--->--->"books":[
                |--->--->--->{
                |--->--->--->--->"id":100,
                |--->--->--->--->"name":"LINQ in Action",
                |--->--->--->--->"edition":1,
                |--->--->--->--->"price":30.19,
                |--->--->--->--->"authors":[
                |--->--->--->--->--->{"id":3,"firstName":"Steve","lastName":"Eichert","gender":"MALE"},
                |--->--->--->--->--->{"id":4,"firstName":"Jim","lastName":"Wooley","gender":"MALE"}
                |--->--->--->--->]
                |--->--->--->}
                |--->--->]
                |--->}, 
                |--->{
                |--->--->"id":100,
                |--->--->"name":"AMAZON",
                |--->--->"website":null,
                |--->--->"books":[
                |--->--->--->{
                |--->--->--->--->"id":101,
                |--->--->--->--->"name":"SQL Crash Course",
                |--->--->--->--->"edition":1,
                |--->--->--->--->"price":23.99,
                |--->--->--->--->"authors":[
                |--->--->--->--->--->{"id":5,"firstName":"Vajo","lastName":"Lukic","gender":"MALE"}
                |--->--->--->--->]
                |--->--->--->},
                |--->--->--->{
                |--->--->--->--->"id":102,
                |--->--->--->--->"name":"SQL Crash Course",
                |--->--->--->--->"edition":2,
                |--->--->--->--->"price":24.99,
                |--->--->--->--->"authors":[
                |--->--->--->--->--->{"id":5,"firstName":"Vajo","lastName":"Lukic","gender":"MALE"}
                |--->--->--->--->]
                |--->--->--->}
                |--->--->]
                |--->}
                |]""".trimMargin(),
            refetchedStores.toString()
        )
    }
}