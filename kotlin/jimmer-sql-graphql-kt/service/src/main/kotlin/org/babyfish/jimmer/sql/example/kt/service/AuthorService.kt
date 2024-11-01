package org.babyfish.jimmer.sql.example.kt.service

import graphql.schema.DataFetchingEnvironment
import org.babyfish.jimmer.spring.graphql.toFetcher
import org.babyfish.jimmer.sql.example.kt.model.Author
import org.babyfish.jimmer.sql.example.kt.repository.AuthorRepository
import org.babyfish.jimmer.sql.example.kt.service.dto.AuthorInput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

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
class AuthorService(
    private val authorRepository: AuthorRepository
) {

    // --- Query ---

    @QueryMapping
    fun authors(
        @Argument name: String?,
        env: DataFetchingEnvironment
    ): List<Author> =
        authorRepository.findByName(name, env.toFetcher())

    // --- Mutation ---

    @MutationMapping
    fun saveAuthor(
        @Argument input: AuthorInput
    ): Author =
        authorRepository.save(input).modifiedEntity

    @MutationMapping
    fun deleteAuthor(@Argument id: Long): Int {
        authorRepository.deleteById(id)
        // GraphQL requires return value,
        // but `deleteById` of spring data return nothing!
        // Is there better design?
        return 1
    }
}