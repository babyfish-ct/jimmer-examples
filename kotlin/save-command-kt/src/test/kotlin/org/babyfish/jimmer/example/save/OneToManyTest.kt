package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.Book
import org.babyfish.jimmer.example.save.model.BookStore
import org.babyfish.jimmer.example.save.model.addBy
import org.babyfish.jimmer.sql.DissociateAction
import org.babyfish.jimmer.sql.runtime.SaveException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal


/**
 * Recommended learning sequence: 4
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * [current: OneToManyTest] -> ManyToManyTest -> BulkTest -> RecursiveTest -> TriggerTest
 */
class OneToManyTest() : AbstractMutationTest() {
    
    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */
    
    @Test
    fun testAttachChildByShortAssociation() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L,
            "SQL in Action",
            1,
            BigDecimal(45)
        )

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    id = 1L
                }
            }
        )

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
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and 
                    |--->--->STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?))
                    |)""".trimMargin(),
                100L, listOf(1L)
            ),

            // Detach unnecessary books.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                "delete from BOOK " +
                    "where STORE_ID = ? and not (ID = any(?))",
                100L, listOf(1L)
            )
        )

        Assertions.assertEquals(2, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
    }

    @Test
    fun testAttachChildByAssociationBasedOnKey() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1,
            "SQL in Action",
            1,
            BigDecimal(49)
        )

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            2,
            "Redis in Action",
            1,
            BigDecimal(39)
        )

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    name = "SQL in Action"
                    edition = 1
                }
                books().addBy {
                    name = "Redis in Action"
                    edition = 1
                }
            }
        )

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
                listOf("SQL in Action", 1, 100L),
                listOf("Redis in Action", 1, 100L)
            ),

            // Before detach unnecessary books, the associations
            // based on `Book.authors` need to be detached first.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and 
                    |--->--->STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?))
                    |)""".trimMargin(),
                100L, listOf(1L, 2L)
            ),

            // Detach unnecessary books.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                "delete from BOOK " +
                    "where STORE_ID = ? and not (ID = any(?))",
                100L, listOf(1L, 2L)
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::class))
    }

    @Test
    fun testUpdateWithAssociation() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            1,
            "SQL in Action",
            1,
            BigDecimal(45),
            1L
        )
        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            2,
            "Redis in Action",
            1,
            BigDecimal(35),
            1L
        )

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    name = "SQL in Action"
                    edition = 1
                    price = BigDecimal(49)
                }
                books().addBy {
                    name = "Redis in Action"
                    edition = 1
                    price = BigDecimal(39)
                }
            }
        )

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
                listOf("SQL in Action", 1, BigDecimal(49), 1L),
                listOf("Redis in Action", 1, BigDecimal(39), 1L)
            ),

            // Before detach unnecessary books, the associations
            // based on `Book.authors` need to be detached first.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and 
                    |--->--->STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?))
                    |)""".trimMargin(),
                1L, listOf(1L, 2L)
            ),

            // Detach unnecessary books.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                "delete from BOOK " +
                    "where STORE_ID = ? and not (ID = any(?))",
                1L, listOf(1L, 2L)
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::class))
    }

    @Test
    fun testAttachChild() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    name = "SQL in Action"
                    edition = 1
                    price = BigDecimal(49)
                }
                books().addBy {
                    name = "Redis in Action"
                    edition = 1
                    price = BigDecimal(39)
                }
            }
        )

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
                listOf("SQL in Action", 1, BigDecimal(49), 1L),
                listOf("Redis in Action", 1, BigDecimal(39), 1L)
            ),

            // Before detach unnecessary books, the associations
            // based on `Book.authors` need to be detached first.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and 
                    |--->--->STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?))
                    |)""".trimMargin(),
                1L, listOf(100L, 101L)
            ),

            // Detach unnecessary books.
            // However, nothing will be deleted in this case
            ExecutedStatement.of(
                "delete from BOOK " +
                    "where STORE_ID = ? and not (ID = any(?))",
                1L, listOf(100L, 101L)
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::class))
    }

    @Test
    fun testDetachChildFailed() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L,
            "SQL in Action",
            1,
            BigDecimal(45)
        )

        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            21,
            "GraphQL in Action",
            1,
            BigDecimal(39),
            1L
        )

        val ex = Assertions.assertThrows(SaveException::class.java) {
            sql.save(
                BookStore {
                    name = "MANNING"
                    books().addBy {
                        id = 1L
                    }
                }
            ) {
                setDissociateAction(Book::store, DissociateAction.NONE)
            }
        }

        Assertions.assertEquals(
            """Save error caused by the path: "<root>.books": 
                |Cannot dissociate child objects because the 
                |dissociation action of the many-to-one property 
                |"org.babyfish.jimmer.example.save.model.Book.store" 
                |is not configured as "set null" or "cascade". 
                |There are two ways to resolve this issue: 
                |Decorate the many-to-one property 
                |"org.babyfish.jimmer.example.save.model.Book.store" 
                |by @org.babyfish.jimmer.sql.OnDissociate whose 
                |argument is `DissociateAction.SET_NULL` or 
                |`DissociateAction.DELETE`, or use save command's 
                |runtime configuration to override it""".trimMargin().toOneLine(),
            ex.message
        )

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
                """select tb_1_.ID from BOOK tb_1_ 
                    |where 
                    |--->tb_1_.STORE_ID = ? 
                    |and 
                    |--->tb_1_.ID <> ? 
                    |limit ?""".trimMargin(),
                1L, 1L, 1
            )
        )
    }

    @Test
    fun testDetachChildByClearingForeignKey() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            10L,
            "SQL in Action",
            1,
            BigDecimal(45)
        )
        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            1L,
            "GraphQL in Action",
            1,
            BigDecimal(49),
            1L
        )
        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            2L,
            "Redis in Action",
            1,
            BigDecimal(39),
            1L
        )

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    id = 1L
                }
            }
        ) {
            setDissociateAction(Book::store, DissociateAction.SET_NULL)
        }

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
                1L, listOf(1L)
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::class))
    }

    @Test
    fun testDetachChildByDeletingChild() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L,
            "SQL in Action",
            1,
            BigDecimal(45)
        )
        jdbc(
            "insert into book(id, name, edition, price, store_id) values(?, ?, ?, ?, ?)",
            2L,
            "GraphQL in Action",
            1,
            BigDecimal(39),
            1L
        )

        val result = sql.save(
            BookStore {
                name = "MANNING"
                books().addBy {
                    id = 1L
                }
            }
        ) {
            setDissociateAction(Book::store, DissociateAction.DELETE)
        }

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
                """delete from BOOK_AUTHOR_MAPPING tb_1_ 
                    |where exists (
                    |--->select * 
                    |--->from BOOK tb_2_ 
                    |--->where 
                    |--->--->tb_1_.BOOK_ID = tb_2_.ID 
                    |--->and STORE_ID = ? 
                    |--->and 
                    |--->--->not (ID = any(?)))""".trimMargin(),
                1L, listOf(1L)
            ),

            // Now, `book-20` can be deleted safely
            ExecutedStatement.of(
                "delete from BOOK " +
                    "where STORE_ID = ? and not (ID = any(?))",
                1L, listOf(1L)
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::class))
    }
}