package org.babyfish.jimmer.sql.example.kt.service

import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.spring.model.SortUtils
import org.babyfish.jimmer.sql.example.kt.model.Book
import org.babyfish.jimmer.sql.example.kt.model.by
import org.babyfish.jimmer.sql.example.kt.repository.BookRepository
import org.babyfish.jimmer.sql.example.kt.service.dto.BookInput
import org.babyfish.jimmer.sql.example.kt.service.dto.BookSpecification
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.babyfish.jimmer.sql.exception.SaveException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

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
@RequestMapping("/book")
@Transactional
class BookService(
    private val bookRepository: BookRepository
) {

    @GetMapping("/simpleList")
    fun findSimpleBooks(): List<@FetchBy("SIMPLE_FETCHER") Book> = // (1)
        bookRepository.findAll(SIMPLE_FETCHER) {
            asc(Book::name)
        }

    /**
     * The functionality of this method is the same as .[findBooksBySuperQBE]
     */
    @GetMapping("/list")
    fun findBooks(
        @RequestParam(defaultValue = "0") pageIndex: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        // The `sortCode` also support implicit join, like `store.name asc`
        @RequestParam(defaultValue = "name asc, edition desc") sortCode: String,
        @RequestParam name: String?,
        @RequestParam minPrice: BigDecimal?,
        @RequestParam maxPrice: BigDecimal?,
        @RequestParam storeName: String?,
        @RequestParam authorName: String?,
    ): Page<@FetchBy("DEFAULT_FETCHER") Book> = // (2)
        bookRepository.findBooks(
            PageRequest.of(pageIndex, pageSize, SortUtils.toSort(sortCode)),
            name,
            minPrice,
            maxPrice,
            storeName,
            authorName,
            DEFAULT_FETCHER
        )

    /**
     * The functionality of this method is the same as .[findBooks]
     */
    @GetMapping("/listBySuperQBE")
    fun findBooksBySuperQBE(
        @RequestParam(defaultValue = "0") pageIndex: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        // The `sortCode` also support implicit join, like `store.name asc`
        @RequestParam(defaultValue = "name asc, edition desc") sortCode: String,
        specification: BookSpecification
    ): Page<@FetchBy("DEFAULT_FETCHER") Book> =
        bookRepository.find(
            PageRequest.of(pageIndex, pageSize, SortUtils.toSort(sortCode)),
            specification,
            DEFAULT_FETCHER
        )

    @GetMapping("/{id}")
    fun findComplexBook(
        @PathVariable id: Long,
    ): @FetchBy("COMPLEX_FETCHER") Book? = // (3)
        bookRepository.findById(id, COMPLEX_FETCHER)

    @PutMapping
    @Throws(SaveException::class) // (4)
    fun saveBook(
        @RequestBody input: BookInput
    ): @FetchBy("DEFAULT_FETCHER") Book = // (5)
        bookRepository
            .saveCommand(input)
            .execute(DEFAULT_FETCHER)
            .modifiedEntity

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long) {
        bookRepository.deleteById(id)
    }

    companion object {

        private val SIMPLE_FETCHER = newFetcher(Book::class).by {
            name()
        }

        private val DEFAULT_FETCHER = newFetcher(Book::class).by {

            allScalarFields()
            tenant(false)

            store {
                name()
            }
            authors {
                firstName()
                lastName()
            }
        }

        private val COMPLEX_FETCHER = newFetcher(Book::class).by {

            allScalarFields()
            tenant(false)

            store {
                allScalarFields()
                avgPrice()
            }
            authors {
                allScalarFields()
            }
        }
    }
}

/*----------------Documentation Links----------------
(1) (2) (3) https://babyfish-ct.github.io/jimmer-doc/docs/spring/client/api#declare-fetchby
(4) (6) https://babyfish-ct.github.io/jimmer-doc/docs/spring/client/error#allow-to-throw-all-exceptions-of-family
(5) (7) https://babyfish-ct.github.io/jimmer-doc/docs/mutation/save-command/input-dto/
---------------------------------------------------*/
