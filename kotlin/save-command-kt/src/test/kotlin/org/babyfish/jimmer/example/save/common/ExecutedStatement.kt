package org.babyfish.jimmer.example.save.common

import org.babyfish.jimmer.sql.runtime.DbLiteral

data class ExecutedStatement private constructor(
    val sql: String,
    val variables: List<Any?>
) {
    constructor(sql: String, vararg variables: Any?) :
        this(
            sql,
            variables.map {
                if (it is DbLiteral.DbNull) {
                    null
                } else {
                    it
                }
            }
        )
}