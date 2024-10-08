package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.*
import org.babyfish.jimmer.kt.new
import org.babyfish.jimmer.sql.runtime.SaveException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal


/**
 * Recommended learning sequence: 5
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> [Current: ManyToManyTest] -> RecursiveTest -> TriggerTest
 */
class ManyToManyTest : AbstractMutationTest() {
    
    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */
    
    @Test
    fun testInsertMiddleTableByShortAssociation() {
        
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            1L, "Fabrice", "Marguerie", "M"
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            2L, "Steve", "Eichert", "M"
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            3L, "Jim", "Wooley", "M"
        )
        
        val result = sql.save(
            new(Book::class).by {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
                authors().addBy { 
                    id = 1L
                }
                authors().addBy {
                    id = 2L
                }
                authors().addBy {
                    id = 3L
                }
            }
        )
        
        assertExecutedStatements( 
            
            // Merge aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) " +
                    "key(NAME, EDITION) values(?, ?, ?)",
                "SQL in Action", 1, BigDecimal(49)
            ),

            // Detach unnecessary associations based on `Book.authors`
            ExecutedStatement.of(
                "delete from BOOK_AUTHOR_MAPPING " +
                    "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                1L, listOf(1L, 2L, 3L)
            ),  
            
            // Attach new associations based on `Book.authors`
            ExecutedStatement.batchOf(
                "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                    "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                    "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                    "when not matched then " +
                    "insert(BOOK_ID, AUTHOR_ID) " +
                    "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                listOf(1L, 1L),
                listOf(1L, 2L),
                listOf(1L, 3L)
            )
        )
        Assertions.assertEquals(4, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
        Assertions.assertEquals(3, result.affectedRowCount(Book::authors))
    }

    @Test
    fun testDeleteMiddleTableByShortAssociation() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "LINQ in Action", 1, BigDecimal(45)
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            1L, "Fabrice", "Brumm", "M"
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            2L, "Steve", "Eichert", "M"
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            3L, "Jim", "Wooley", "M"
        )
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 1L)
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 2L)
        jdbc("insert into book_author_mapping(book_id, author_id) values(?, ?)", 1L, 3L)

        val result = sql.save(
            new(Book::class).by {
                name = "LINQ in Action"
                edition = 1
                price = BigDecimal(49)
                authors().addBy {
                    id = 1L
                }
                // Only `Author-1` is specified here,
                // so old associations to `Author-2`
                // and `Author-3` will be detached
            }
        )

        assertExecutedStatements(

            // Merge aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) key(NAME, EDITION) values(?, ?, ?)",
                "LINQ in Action", 1, BigDecimal(49)
            ),

            // Detach unnecessary associations base on `Book.authors`
            ExecutedStatement.of(
                "delete from BOOK_AUTHOR_MAPPING " +
                    "where BOOK_ID = ? and AUTHOR_ID <> ?",
                1L, 1L
            ),
            
            ExecutedStatement.of(
                "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                    "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                    "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                    "when not matched then " +
                    "insert(BOOK_ID, AUTHOR_ID) " +
                    "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                1L, 1L
            )
        )

        Assertions.assertEquals(3, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
        Assertions.assertEquals(2, result.affectedRowCount(Book::authors))
    }

    @Test
    fun testIllegalShortAssociation() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "LINQ in Action", 1, BigDecimal(45)
        )
        jdbc(
            "insert into author(id, first_name, last_name, gender) values(?, ?, ?, ?)",
            1L, "Fabrice", "Marguerie", "M"
        )

        val ex = Assertions.assertThrows(SaveException::class.java) {
            sql.save(
                new(Book::class).by {
                    name = "LINQ in Action"
                    edition = 1
                    price = BigDecimal(49)
                    authors().addBy {
                        id = 1L
                    }
                    authors().addBy {
                        id = 88888L
                    }
                    authors().addBy {
                        id = 99999L
                    }
                }
            )
        }

        Assertions.assertEquals(
            "Save error caused by the path: \"<root>.authors\": " +
                "Cannot save the entity, the associated id of the reference property " +
                "\"org.babyfish.jimmer.example.save.model.Book.authors\" is \"88888\" " +
                "but there is no corresponding associated object in the database",
            ex.message
        )

        assertExecutedStatements(

            // Merge aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) key(NAME, EDITION) values(?, ?, ?)",
                "LINQ in Action", 1, BigDecimal(49)
            ),

            // Detach unnecessary associations based on `Book.authors`
            ExecutedStatement.of(
                "delete from BOOK_AUTHOR_MAPPING " +
                    "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                1L, listOf(1L, 88888L, 99999L)
            ),

            // Attach new associations based on `Book.authors`
            ExecutedStatement.batchOf(
                "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                    "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                    "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                    "when not matched then " +
                    "insert(BOOK_ID, AUTHOR_ID) " +
                    "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                listOf(1L, 1L),
                listOf(1L, 88888L),
                listOf(1L, 99999L)
            ),

            // Investigate why the database throws
            // error about constraint violation
            ExecutedStatement.of(
                "select tb_1_.ID from AUTHOR tb_1_ " +
                    "where tb_1_.ID = ?",
                88888L
            )
        )
    }

    @Test
    fun testInsertMiddleTableByLongAssociation() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "LINQ in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            new(Book::class).by {
                name = "LINQ in Action"
                edition = 1
                price = BigDecimal(49)
                authors().addBy {
                    firstName = "Fabrice"
                    lastName = "Marguerie"
                    gender = Gender.MALE
                }
                authors().addBy {
                    firstName = "Steve"
                    lastName = "Eichert"
                    gender = Gender.MALE
                }
                authors().addBy {
                    firstName = "Jim"
                    lastName = "Wooley"
                    gender = Gender.MALE
                }
            }
        )

        assertExecutedStatements(

            // Merge aggregate-root
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) " +
                    "key(NAME, EDITION) " +
                    "values(?, ?, ?)",
                "LINQ in Action", 1, BigDecimal(49)
            ),

            // Merge associated objects
            ExecutedStatement.batchOf(
                "merge into AUTHOR(FIRST_NAME, LAST_NAME, GENDER) " +
                    "key(FIRST_NAME, LAST_NAME) " +
                    "values(?, ?, ?)",
                listOf("Fabrice", "Marguerie", "M"),
                listOf("Steve", "Eichert", "M"),
                listOf("Jim", "Wooley", "M")
            ),

            // Detach unnecessary associations based on `Book.authors`
            ExecutedStatement.of(
                "delete from BOOK_AUTHOR_MAPPING " +
                    "where BOOK_ID = ? and not (AUTHOR_ID = any(?))",
                1L, listOf(100L, 101L, 102L)
            ),

            // Attach new associations based on `Book.authors`
            ExecutedStatement.batchOf(
                "merge into BOOK_AUTHOR_MAPPING tb_1_ " +
                    "using(values(?, ?)) tb_2_(BOOK_ID, AUTHOR_ID) " +
                    "on tb_1_.BOOK_ID = tb_2_.BOOK_ID and tb_1_.AUTHOR_ID = tb_2_.AUTHOR_ID " +
                    "when not matched then " +
                    "insert(BOOK_ID, AUTHOR_ID) " +
                    "values(tb_2_.BOOK_ID, tb_2_.AUTHOR_ID)",
                listOf(1L, 100L),
                listOf(1L, 101L),
                listOf(1L, 102L)
            )
        )

        Assertions.assertEquals(7, result.totalAffectedRowCount)
        Assertions.assertEquals(1, result.affectedRowCount(Book::class))
        Assertions.assertEquals(3, result.affectedRowCount(Author::class))
        Assertions.assertEquals(3, result.affectedRowCount(Book::authors))
    }
}