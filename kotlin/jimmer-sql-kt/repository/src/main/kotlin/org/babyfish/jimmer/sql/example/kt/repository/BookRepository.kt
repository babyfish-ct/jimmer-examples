package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.spring.repository.fetchSpringPage
import org.babyfish.jimmer.spring.repository.orderBy
import org.babyfish.jimmer.sql.example.kt.model.*
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class BookRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<Book, Long>(sql) {

    /**
     * Manually implement complex query.
     *
     * <p>The functionality of this method is the same as the super QBE method.[find]</p>
     */
    fun findBooks(
        pageable: Pageable,
        name: String?,
        minPrice: BigDecimal?,
        maxPrice: BigDecimal?,
        storeName: String?,
        authorName: String?,
        fetcher: Fetcher<Book>?
    ): Page<Book> =
        sql.createQuery(Book::class) {
            where(table.name `eq?` name)
            where(table.price.`between?`(minPrice, maxPrice))
            where(table.store.name `eq?` storeName)
            where += table.authors {
                or(
                    firstName `ilike?` authorName,
                    lastName `ilike?` authorName
                )
            }
            orderBy(pageable.sort)
            select(table.fetch(fetcher))
        }.fetchSpringPage(pageable)

    /**
     * Super QBE.
     *
     * <p>The functionality of this method is the same as the manual method .[findBooks]
     * </p>
     */
    fun find(
        pageable: Pageable,
        specification: KSpecification<Book>,
        fetcher: Fetcher<Book>?
    ): Page<Book> =
        sql.createQuery(Book::class) {
            where(specification)
            select(table.fetch(fetcher))
        }.fetchSpringPage(pageable)

    fun findAvgPriceGroupByStoreIds(storeIds: Collection<Long>): Map<Long, BigDecimal> =
        sql.executeQuery(Book::class) {
            where(table.storeId valueIn storeIds)
            groupBy(table.storeId)
            select(
                table.storeId.asNonNull(),
                avgAsDecimal(table.price).asNonNull()
            )
        }.associateBy({it._1}) {
            it._2
        }

    fun findNewestIdsGroupByStoreIds(storeIds: Collection<Long>): Map<Long, List<Long>> =
        sql.executeQuery(Book::class ) {
            where(
                tuple(table.storeId, table.name, table.edition) valueIn subQuery(Book::class) {
                    // Apply `filter` for sub query is better.
                    where(table.storeId valueIn storeIds)
                    groupBy(table.storeId, table.name)
                    select(
                        table.storeId,
                        table.name,
                        max(table.edition).asNonNull()
                    )
                }
            )
            select(
                table.storeId.asNonNull(),
                table.id
            )
        }.groupBy({it._1}) {
            it._2
        }
}
