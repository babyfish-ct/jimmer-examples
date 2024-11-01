package org.babyfish.jimmer.sql.example.kt.runtime.resolver

import org.babyfish.jimmer.kt.toImmutableProp
import org.babyfish.jimmer.lang.Ref
import org.babyfish.jimmer.sql.event.AssociationEvent
import org.babyfish.jimmer.sql.event.EntityEvent
import org.babyfish.jimmer.sql.example.kt.model.Book
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.example.kt.repository.BookRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.KTransientResolver
import org.babyfish.jimmer.sql.kt.event.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookStoreNewestBooksResolver(
    private val sql: KSqlClient,
    private val bookRepository: BookRepository
) : KTransientResolver<Long, List<Long>> { // (1)

    override fun resolve(ids: Collection<Long>): Map<Long, List<Long>> = // (2)
        bookRepository.findNewestIdsGroupByStoreIds(ids)

    override fun getDefaultValue(): List<Long> = // (3)
        emptyList()

    // -----------------------------
    // If you are a beginner, you can ignore all the following code.
    //
    // The following code is only used for cache mode(start the application
    // by `application.yml`).
    //
    // Unlike the fully automatic cache consistency maintenance of
    // ordinary associated property, if a calculated property uses cache,
    // its consistency requires manual assistance.
    // -----------------------------

    
    
    
    // The calculated property `BookStore.newestBooks` depends on the one-to-many association `BookStore.books`,
    // and `BookStore.books` adopts multi-view cache because its target type `Book` is processed by the
    // filter `TenantFilter`, so `BookStore.newestBooks` should also adopt multi-view cache too.
    //
    // Since it is multi-view cache, sub key is needed.
    // Here, we make the calculated cache `BookStore.newestBooks` have the same sub key as the
    // association cache `BookStore.books`, which is `{"tenant": ...}`
    override fun getParameterMapRef(): Ref<SortedMap<String, Any>?>? { // (4)
        return sql.filters.getTargetParameterMapRef(BookStore::books)
    }

    // When a one-to-many association `BookStore.books` is modified
    // (for some records in the BOOK table, whether by modifying the foreign key field `STORE_ID` or
    // the field `TENANT` that the `TenantFilter` cares about),
    // the cache of the calculated property `BookStore.newestBooks` should be invalidated.
    override fun getAffectedSourceIds(e: AssociationEvent): Collection<*>? { // (5)
        return if (sql.caches.isAffectedBy(e) && e.immutableProp === BookStore::books.toImmutableProp()) {
            listOf(e.sourceId)
        } else null
    }

    // Given that the foreign key `STORE_ID` of the current `Book` is not null and has not been modified,
    // if the `edition` of the current `Book` changes, the cache of the computed property `BookStore.newestBooks`
    // corresponding to `STORE_ID` should be invalidated.
    override fun getAffectedSourceIds(e: EntityEvent<*>): Collection<*>? { // (6)
        if (sql.caches.isAffectedBy(e) &&
            !e.isEvict &&
            e.getImmutableType().javaClass == Book::class.java
        ) {
            val store = e.getUnchangedRef(Book::store)?.value
            if (store !== null && e.isChanged(Book::edition)) {
                return listOf(store.id)
            }
        }
        return null
    }
}

/*----------------Documentation Links----------------
(1) (2) (3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/transient#scalar-calculation-bookstoreavgprice
(4) https://babyfish-ct.github.io/jimmer/docs-doc/cache/multiview-cache/user-filter#subkey-of-calculated-properties
(5) (6) https://babyfish-ct.github.io/jimmer-doc/docs/cache/multiview-cache/user-filter#consistency
---------------------------------------------------*/
