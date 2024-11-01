package org.babyfish.jimmer.sql.example.service;

import graphql.schema.DataFetchingEnvironment;
import org.babyfish.jimmer.spring.graphql.DataFetchingEnvironments;
import org.babyfish.jimmer.sql.example.model.BookStore;
import org.babyfish.jimmer.sql.example.repository.BookStoreRepository;
import org.babyfish.jimmer.sql.example.service.dto.BookStoreInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreService(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    // --- Query ---

    @QueryMapping
    public List<BookStore> bookStores(
            @Argument @Nullable String name,
            DataFetchingEnvironment env
    ) {
        return bookStoreRepository.findByNameLikeOrderByName(
                name,
                DataFetchingEnvironments.createFetcher(BookStore.class, env)
        );
    }

    // --- Mutation ---

    @MutationMapping
    @Transactional
    public BookStore saveBookStore(@Argument BookStoreInput input) {
        return bookStoreRepository.save(input).getModifiedEntity();
    }

    @MutationMapping
    @Transactional
    public int deleteBookStore(@Argument long id) {
        bookStoreRepository.deleteById(id);
        // GraphQL requires return value,
        // but `deleteById` of spring-data does not support value.
        // Is there a better design?
        return 1;
    }
}
