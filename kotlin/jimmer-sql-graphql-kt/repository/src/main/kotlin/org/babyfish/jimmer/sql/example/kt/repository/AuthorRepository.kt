package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.spring.repository.orderBy
import org.babyfish.jimmer.sql.example.kt.model.Author
import org.babyfish.jimmer.sql.example.kt.model.firstName
import org.babyfish.jimmer.sql.example.kt.model.lastName
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.or
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<Author, Long>(sql) {

    fun findByName(name: String?, fetcher: Fetcher<Author>?): List<Author> =
        sql.executeQuery(Author::class) {
            name?.let {
                where(
                    or(
                        table.firstName eq it,
                        table.lastName eq it
                    )
                )
            }
            select(table.fetch(fetcher))
        }
}
