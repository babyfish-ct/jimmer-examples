package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.example.kt.model.name
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.springframework.stereotype.Repository

@Repository
class BookStoreRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<BookStore, Long>(sql) {

    fun findByNameLikeOrderByName(
        name: String?,
        fetcher: Fetcher<BookStore>?
    ): List<BookStore> =
        sql.executeQuery(BookStore::class) {
            where(table.name `eq?` name)
            select(table.fetch(fetcher))
        }
}

