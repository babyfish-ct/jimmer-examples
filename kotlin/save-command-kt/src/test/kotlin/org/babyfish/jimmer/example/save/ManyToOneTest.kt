package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.Book
import org.babyfish.jimmer.example.save.model.BookStore
import org.babyfish.jimmer.kt.makeIdOnly
import org.babyfish.jimmer.sql.exception.SaveException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal


/**
 * Recommended learning sequence: 3
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> [current: ManyToOneTest] ->
 * OneToManyTest -> ManyToManyTest -> BulkTest -> RecursiveTest -> TriggerTest
 */
class ManyToOneTest : AbstractMutationTest() {
    
    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */
    
    @Test
    fun testShortAssociation() {
        
        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )
        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
                store = makeIdOnly(1L)
            }
        )
        
        assertExecutedStatements(

            // Merge the associated object.
            //
            // Associated object is parent object but aggregate-root is child object,
            // so it is handled before aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                    "key(NAME, EDITION) " +
                    "values(?, ?, ?, ?)",
                "SQL in Action", 1, BigDecimal(49), 1L
            )
        )
        Assertions.assertEquals(1, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
    }

    @Test
    fun testIllegalShortAssociation() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            10L, "SQL in Action", 1, BigDecimal(45)
        )

        val ex = Assertions.assertThrows(SaveException::class.java) {
            sql.save(
                Book {
                    name = "SQL in Action"
                    edition = 1
                    price = BigDecimal(49)
                    store = makeIdOnly(99999L)
                }
            )
        }

        Assertions.assertEquals(
            "Save error caused by the path: \"<root>.store\": " +
                "Cannot save the entity, the associated id of the reference property " +
                "\"org.babyfish.jimmer.example.save.model.Book.store\" is \"99999\" " +
                "but there is no corresponding associated object in the database",
            ex.message
        )

        assertExecutedStatements(

            // Merge the associated object.
            //
            // Associated object is parent object but aggregate-root is child object,
            // so it is handled before aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE, STORE_ID) " +
                    "key(NAME, EDITION) " +
                    "values(?, ?, ?, ?)",
                "SQL in Action", 1, BigDecimal(49), 99999L
            ),

            // Investigate why the database throws
            // error about constraint violation
            ExecutedStatement.of(
                "select tb_1_.ID " +
                    "from BOOK_STORE tb_1_ " +
                    "where tb_1_.ID = ?",
                99999L
            )
        )
    }

    @Test
    fun testAssociationByKey() {

        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
                store().apply {
                    name = "MANNING"
                }
            }
        )
        
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
                "SQL in Action", 1, BigDecimal(49), 1L
            )
        )

        Assertions.assertEquals(2, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
    }

    @Test
    fun testAssociationByExistingParent() {
        
        jdbc("insert into book_store(id, name) values(?, ?)", 1L, "MANNING")
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )
        
        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
                store().apply {
                    name = "MANNING"
                    website = "https://www.manning.com"
                }
            }
        )

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
                "SQL in Action", 1, BigDecimal(49), 1L
            )
        )

        Assertions.assertEquals(2, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
    }

    @Test
    fun testAttachParent() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
                store().apply {
                    name = "TURING"
                    website = "https://www.turing.com"
                }
            }
        )

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
                "SQL in Action", 1, BigDecimal(49), 100L
            ),
        )
        Assertions.assertEquals(2, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(BookStore::class))
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
    }
}