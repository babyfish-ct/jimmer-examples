package org.babyfish.jimmer.sql.example.kt.service

import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.example.kt.model.by
import org.babyfish.jimmer.sql.example.kt.repository.BookStoreRepository
import org.babyfish.jimmer.sql.example.kt.service.dto.BookStoreInput
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.babyfish.jimmer.sql.exception.SaveException
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/*
 * Why add spring web annotations to the service class?
 *
 * The success and popularity of rich client technologies represented by React, Vue and Angular
 * have greatly reduced the significance of the Controller layer on the spring server side.
 *
 * Moreover, over-bloated code structures are not conducive to demonstrating the capabilities
 * of the framework with small examples. Therefore, this example project no longer adheres to
 * dogmatism and directly adds spring web annotations to the service class.
 */
@RestController
@RequestMapping("/bookStore")
@Transactional
class BookStoreService(
    private val bookStoreRepository: BookStoreRepository
) {

    @GetMapping("/simpleList")
    fun findSimpleStores(): List<@FetchBy("SIMPLE_FETCHER") BookStore> = // (1)
        bookStoreRepository.findAll(SIMPLE_FETCHER) {
            asc(BookStore::name)
        }

    @GetMapping("/list")
    fun findStores(): List<@FetchBy("DEFAULT_FETCHER") BookStore> = // (2)
        bookStoreRepository.findAll(DEFAULT_FETCHER) {
            asc(BookStore::name)
        }

    @GetMapping("/complexList")
    fun findComplexStores(): List<BookStore> { // (3)
        return bookStoreRepository.findAll(WITH_ALL_BOOKS_FETCHER) {
            asc(BookStore::name)
        }
    }

    @GetMapping("/{id}/withAllBooks")
    fun findComplexStoreWithAllBooks(
        @PathVariable id: Long
    ): @FetchBy("WITH_ALL_BOOKS_FETCHER") BookStore? = // (4)
        bookStoreRepository.findNullable(id, WITH_ALL_BOOKS_FETCHER)

    @GetMapping("/{id}/withNewestBooks")
    fun findComplexStoreWithNewestBooks(
        @PathVariable id: Long
    ): @FetchBy("WITH_NEWEST_BOOKS_FETCHER") BookStore? = // (5)
        bookStoreRepository.findNullable(id, WITH_NEWEST_BOOKS_FETCHER)

    @PutMapping
    @Throws(SaveException::class) // (6)
    fun saveBookStore(@RequestBody input: BookStoreInput): BookStore = // (7)
        bookStoreRepository.save(input)

    @DeleteMapping("{id}")
    fun deleteBookStore(@PathVariable id: Long) {
        bookStoreRepository.deleteById(id)
    }

    companion object {

        private val SIMPLE_FETCHER = newFetcher(BookStore::class).by {
            name()
        }

        private val DEFAULT_FETCHER = newFetcher(BookStore::class).by {
            allScalarFields()
        }

        private val WITH_ALL_BOOKS_FETCHER = newFetcher(BookStore::class).by {
            allScalarFields()
            avgPrice()
            books {
                allScalarFields()
                tenant(false)
                authors {
                    allScalarFields()
                }
            }
        }

        private val WITH_NEWEST_BOOKS_FETCHER = newFetcher(BookStore::class).by {
            allScalarFields()
            avgPrice()
            newestBooks {
                allScalarFields()
                tenant(false)
                authors {
                    allScalarFields()
                }
            }
        }
    }
}

/*----------------Documentation Links----------------
(1) (2) (3) (4) (5) https://babyfish-ct.github.io/jimmer/docs/spring/client/api#declare-fetchby
(6) https://babyfish-ct.github.io/jimmer/docs/spring/client/error#allow-to-throw-all-exceptions-of-family
(7) https://babyfish-ct.github.io/jimmer/docs/mutation/save-command/input-dto/
---------------------------------------------------*/
