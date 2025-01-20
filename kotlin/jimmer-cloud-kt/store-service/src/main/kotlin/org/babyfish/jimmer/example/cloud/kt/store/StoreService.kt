package org.babyfish.jimmer.example.cloud.kt.store

import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.example.cloud.kt.model.BookStore
import org.babyfish.jimmer.example.cloud.kt.model.by
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@Transactional
class StoreService(
    private val storeRepository: StoreRepository
) {

    /**
     * Load BookStore form current service
     * @param id The id of BookStore
     * @return The BookStore object
     */
    @GetMapping("/store/{id}")
    fun findStore(
        @PathVariable("id") id: Long
    ): @FetchBy("SIMPLE_FETCHER") BookStore? =
        storeRepository.findNullable(id, SIMPLE_FETCHER)

    /**
     * Load BookStore form current service and associated objects from other services
     * @param id The id of BookStore
     * @return The BookStore object and associated objects
     */
    @GetMapping("/store/{id}/detail")
    fun findStoreDetail(
        @PathVariable("id") id: Long
    ): @FetchBy("COMPLEX_FETCHER") BookStore? =
        storeRepository.findNullable(id, COMPLEX_FETCHER)

    companion object {

        val SIMPLE_FETCHER = newFetcher(BookStore::class).by {
            name()
        }

        val COMPLEX_FETCHER = newFetcher(BookStore::class).by {
            allScalarFields()
            books {
                allScalarFields()
                authors {
                    allScalarFields()
                }
            }
        }
    }
}