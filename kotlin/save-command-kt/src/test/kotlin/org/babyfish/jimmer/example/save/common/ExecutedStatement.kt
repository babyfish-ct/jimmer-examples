package org.babyfish.jimmer.example.save.common

import org.babyfish.jimmer.sql.runtime.DbLiteral

data class ExecutedStatement private constructor(
    val sql: String,
    val variableLists: List<List<Any?>>
) {
    companion object {

        fun of(
            sql: String,
            vararg variables: Any?
        ) : ExecutedStatement =
            ExecutedStatement(
                sql.toSimpleSql(),
                listOf(
                    variables.map {
                        if (it is DbLiteral.DbNull) {
                            null
                        } else {
                            it
                        }
                    }
                )
            )

        fun batchOf(
            sql: String,
            vararg variableMatrix: List<Any?>
        ): ExecutedStatement =
            ExecutedStatement(
                sql.toSimpleSql(),
                variableMatrix.map {variables ->
                    variables.map {
                        if (it is DbLiteral.DbNull) {
                            null
                        } else {
                            it
                        }
                    }
                }
            )

        private fun String.toSimpleSql(): String =
            replace("--->", "")
                .replace("\n", "")
                .replace("\r", "")
    }
}