package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.Specification
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.example.kt.model.*
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.babyfish.jimmer.sql.kt.ast.table.makeOrders
import org.springframework.data.domain.Sort
import java.math.BigDecimal

interface BookRepository : KRepository<Book, Long> {

    /**
     * Manually implement complex query.
     *
     * <p>The functionality of this method is the same as the super QBE method.[find]</p>
     */
    fun findBooks(
        name: String?,
        minPrice: BigDecimal?,
        maxPrice: BigDecimal?,
        storeName: String?,
        authorName: String?,
        sortCode: String?,
        fetcher: Fetcher<Book>?
    ): List<Book> =
        sql
            .createQuery(Book::class) {
                where(table.name `eq?` name)
                where(table.price.`between?`(minPrice, maxPrice))
                where(table.store.name `eq?` storeName)
                where += table.authors {
                    or(
                        firstName `ilike?` authorName,
                        lastName `ilike?` authorName
                    )
                }
                orderBy(table.makeOrders(sortCode ?: "name asc"))
                select(table.fetch(fetcher))
            }
            .execute()

    /**
     * Super QBE.
     *
     * <p>The functionality of this method is the same as the manual method .[findBooks]
     * </p>
     */
    fun find(
        specification: Specification<Book>,
        sort: Sort,
        fetcher: Fetcher<Book>?
    ): List<Book>

    fun findAvgPriceGroupByStoreIds(storeIds: Collection<Long>): Map<Long, BigDecimal> =
        sql
            .createQuery(Book::class) {
                where(table.storeId valueIn storeIds)
                groupBy(table.storeId)
                select(
                    table.storeId.asNonNull(),
                    avgAsDecimal(table.price).asNonNull()
                )
            }
            .execute()
            .associateBy({it._1}) {
                it._2
            }

    fun findNewestIdsGroupByStoreIds(storeIds: Collection<Long>): Map<Long, List<Long>> =
        sql
            .createQuery(Book::class) {
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
            }
            .execute()
            .groupBy({it._1}) {
                it._2
            }
}
