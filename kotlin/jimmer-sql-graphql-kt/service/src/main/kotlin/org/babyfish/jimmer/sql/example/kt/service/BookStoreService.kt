package org.babyfish.jimmer.sql.example.kt.service

import graphql.schema.DataFetchingEnvironment
import org.babyfish.jimmer.spring.graphql.toFetcher
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.example.kt.repository.BookStoreRepository
import org.babyfish.jimmer.sql.example.kt.service.dto.BookStoreInput
import org.babyfish.jimmer.sql.example.kt.service.dto.CompositeBookStoreInput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional

/**
 * A real project should be a three-tier architecture consisting
 * of repository, service, and controller.
 *
 * This demo has no business logic, its purpose is only to tell users
 * how to use jimmer with the <b>least</b> code. Therefore, this demo
 * does not follow this convention, and let services be directly
 * decorated by `@Controller`, not `@Service`.
 */
@Controller
@Transactional
class BookStoreService(
    private val bookStoreRepository: BookStoreRepository
) {

    // --- Query ---

    @QueryMapping
    fun bookStores(
        @Argument name: String?,
        env: DataFetchingEnvironment
    ): List<BookStore> {
        return bookStoreRepository.findByNameLikeOrderByName(
            name,
            env.toFetcher()
        )
    }

    // --- Mutation ---
    @MutationMapping
    @Transactional
    fun saveBookStore(
        @Argument input: BookStoreInput,
        env: DataFetchingEnvironment
    ): BookStore =
        bookStoreRepository
            .save(input, env.toFetcher())
            .modifiedEntity

    @MutationMapping
    fun createDeepBookStore(
        @Argument input: CompositeBookStoreInput,
        env: DataFetchingEnvironment
    ): BookStore {
        return bookStoreRepository.save(
            input,
            SaveMode.INSERT_ONLY,
            AssociatedSaveMode.REPLACE,
            env.toFetcher()
        ).modifiedEntity
    }

    @MutationMapping
    fun updateDeepBookStoreById(
        @Argument id: Long,
        @Argument input: CompositeBookStoreInput,
        env: DataFetchingEnvironment
    ): BookStore {
        return bookStoreRepository.save(
            input.toEntity { this.id = id },
            SaveMode.UPDATE_ONLY,
            AssociatedSaveMode.REPLACE,
            env.toFetcher()
        ).modifiedEntity
    }

    @MutationMapping
    fun updateDeepBookStoreByKey(
        @Argument input: CompositeBookStoreInput,
        env: DataFetchingEnvironment
    ): BookStore {
        return bookStoreRepository.save(
            input,
            SaveMode.UPDATE_ONLY,
            AssociatedSaveMode.REPLACE,
            env.toFetcher()
        ).modifiedEntity
    }

    @MutationMapping
    @Transactional
    fun deleteBookStore(@Argument id: Long): Int {
        bookStoreRepository.deleteById(id)
        // GraphQL requires return value,
        // but `deleteById` of spring data return nothing!
        // Is there better design?
        return 1
    }
}