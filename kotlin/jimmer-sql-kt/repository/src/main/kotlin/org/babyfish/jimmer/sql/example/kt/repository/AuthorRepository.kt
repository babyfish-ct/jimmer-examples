package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.spring.repository.orderBy
import org.babyfish.jimmer.sql.example.kt.model.Author
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<Author, Long>(sql) {

    fun find(
        specification: KSpecification<Author>,
        sort: Sort,
        fetcher: Fetcher<Author>?
    ): List<Author> =
        sql.executeQuery(Author::class) {
            where(specification)
            orderBy(sort)
            select(table.fetch(fetcher))
        }
}

