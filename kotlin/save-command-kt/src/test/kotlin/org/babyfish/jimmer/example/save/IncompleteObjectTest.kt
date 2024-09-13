package org.babyfish.jimmer.example.save

import org.babyfish.jimmer.example.save.common.AbstractMutationTest
import org.babyfish.jimmer.example.save.common.ExecutedStatement
import org.babyfish.jimmer.example.save.model.BookStore
import org.babyfish.jimmer.example.save.model.by
import org.babyfish.jimmer.kt.new
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


/**
 * Recommended learning sequence: 2
 *
 *
 * SaveModeTest -> [Current: IncompleteObjectTest] -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> RecursiveTest -> TriggerTest
 */
class IncompleteObjectTest : AbstractMutationTest() {
    @Test
    fun testCompleteObject() {

        jdbc(
            "insert into book_store(id, name, website) values(?, ?, ?)",
            1, "O'REILLY", "http://www.oreilly.com"
        )

        val result = sql.save(
            new(BookStore::class).by {
                id = 1L
                name = "O'REILLY+"

                /*
                 * Jimmer determines how to save data based
                 * on the shape of the object, rather than
                 * simply using "update not null".
                 *
                 * Instead, it leverages the dynamic ability
                 * of Jimmer entities. It focuses on whether
                 * a property of an object has been specified,
                 * rather than whether the value of the property
                 * is null.
                 *
                 * In this example, `Book.store` being specified
                 * as null indicates that this foreign key will
                 * be updated to null.
                 */
                website = null
            }
        ) {
            setMode(SaveMode.UPDATE_ONLY)
        }
            
        assertExecutedStatements(

            ExecutedStatement.of(
                "update BOOK_STORE " +
                    "set NAME = ?, WEBSITE = ? " +
                    "where ID = ?",
                "O'REILLY+", null, 1L
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
    }

    @Test
    fun testIncompleteObject() {
        jdbc(
            "insert into book_store(id, name, website) values(?, ?, ?)",
            1, "O'REILLY", "http://www.oreilly.com"
        )

        val result = sql.save(
            new(BookStore::class).by {
                id = 1L
                name = "O'REILLY+"

                // `website` is not specified
                /*
                 * Jimmer determines how to save data based
                 * on the shape of the object, rather than
                 * simply using "update not null".
                 *
                 * Instead, it leverages the dynamic ability
                 * of Jimmer entities. It focuses on whether
                 * a property of an object has been specified,
                 * rather than whether the value of the property
                 * is null.
                 *
                 * In this example, `Book.store` being not
                 * specifie indicates that this foreign key
                 * will not be updated.
                 */
            }
        ) {
            setMode(SaveMode.UPDATE_ONLY)
        }

        assertExecutedStatements(

            // Unspecified property `website` will not be updated
            ExecutedStatement.of(
                "update BOOK_STORE " +
                    "set NAME = ? " +
                    "where ID = ?",
                "O'REILLY+", 1L
            )
        )

        Assertions.assertEquals(1, result.totalAffectedRowCount)
    }
}