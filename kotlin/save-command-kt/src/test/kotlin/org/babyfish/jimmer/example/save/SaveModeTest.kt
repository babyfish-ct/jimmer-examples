package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.Book
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal


/**
 * Recommended learning sequence: 1
 *
 *
 * [Current: SaveModeTest] -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> RecursiveTest -> TriggerTest
 */
class SaveModeTest : AbstractMutationTest() {

    @Test
    fun testInsertOnly() {
        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
            }
        ) {
            setMode(SaveMode.INSERT_ONLY)
        }

        // `INSERT_ONLY` represents direct insertion regardless of whether the data exists
        assertExecutedStatements(
            ExecutedStatement.of(
                "insert into BOOK(NAME, EDITION, PRICE) values(?, ?, ?)",
                "SQL in Action",
                1, BigDecimal(49)
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)

        // `identity(100, 1)` in DDL
        Assertions.assertEquals(100L, result.modifiedEntity.id)
    }

    @Test
    fun testUpdateOnlyById() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            10L, "SQL in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            Book {
                id = 10L
                name = "SQL in Action"
                edition = 2
                price = BigDecimal(49)
            }
        ) {
            setMode(SaveMode.UPDATE_ONLY)
        }

        // `UPDATE_ONLY` represents direct update regardless of whether the data exists
        assertExecutedStatements(
            ExecutedStatement.of(
                "update BOOK set NAME = ?, EDITION = ?, PRICE = ? where ID = ?",
                "SQL in Action", 2, BigDecimal(49), 10L
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
    }

    @Test
    fun testUpdateExistingDataByKey() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
            }
        ) {
            setMode(SaveMode.UPDATE_ONLY)
        }

        assertExecutedStatements(

            //Although `UPDATE_ONLY` is specified, the id attribute of the object is missing
            // so that it will still result in a key-based query.
            ExecutedStatement.of(
                "update BOOK set PRICE = ? where NAME = ? and EDITION = ?",
                BigDecimal(49), "SQL in Action", 1
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
        Assertions.assertEquals(1L, result.modifiedEntity.id)
    }

    @Test
    fun testUpdateNonExistingDataByKey() {

        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
            }
        ) {
            setMode(SaveMode.UPDATE_ONLY)
        }

        assertExecutedStatements(

            //Although `UPDATE_ONLY` is specified, the id attribute of the object is missing
            // so that it will still result in a key-based query.
            ExecutedStatement.of(
                "update BOOK set PRICE = ? " +
                    "where NAME = ? and EDITION = ?",
                BigDecimal(49), "SQL in Action", 1
            ) // No data can be selected, do nothing(affected row count is 0)
        )

        // Nothing updated
        Assertions.assertEquals(0, result.totalAffectedRowCount)
    }

    @Test
    fun testUpsertExistingDataById() {

        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            1L, "SQL in Action", 1, BigDecimal(45)
        )

        val result = sql.save(
            Book {
                id = 1L
                name = "PL/SQL in Action"
                edition = 2
            }
        )

        assertExecutedStatements(

            // Query whether the data exists by id
            ExecutedStatement.of(
                "merge into BOOK(ID, NAME, EDITION) key(ID) values(?, ?, ?)",
                1L, "PL/SQL in Action", 2
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
    }

    @Test
    fun testUpsertExistingDataByKey() {
        jdbc(
            "insert into book(id, name, edition, price) values(?, ?, ?, ?)",
            10L, "SQL in Action", 1, BigDecimal(45)
        )
        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
            }
        )

        assertExecutedStatements(

            // Query whether the data exists by key
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) " +
                    "key(NAME, EDITION) " +
                    "values(?, ?, ?)",
                "SQL in Action", 1, BigDecimal(49)
            ),
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
    }

    @Test
    fun testUpsertNonExistingDataById() {
        val result = sql.save(
            Book {
                id = 10L
                name = "SQL in Action"
                edition = 2
                price = BigDecimal(49)
            }
        )

        assertExecutedStatements(

            // Query whether the data exists by id
            ExecutedStatement.of(
                "merge into BOOK(ID, NAME, EDITION, PRICE) " +
                    "key(ID) " +
                    "values(?, ?, ?, ?)",
                10L, "SQL in Action", 2, BigDecimal(49)
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
        Assertions.assertEquals(10L, result.modifiedEntity.id)
    }

    @Test
    fun testUpsertNonExistingDataByKey() {
        val result = sql.save(
            Book {
                name = "SQL in Action"
                edition = 1
                price = BigDecimal(49)
            }
        )

        assertExecutedStatements(

            // Query whether the data exists by key
            ExecutedStatement.of(
                "merge into BOOK(NAME, EDITION, PRICE) " +
                    "key(NAME, EDITION) " +
                    "values(?, ?, ?)",
                "SQL in Action", 1, BigDecimal(49)
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
        Assertions.assertEquals(100L, result.modifiedEntity.id)
    }
}