package org.babyfish.jimmer.sql.example.service;

import graphql.schema.DataFetchingEnvironment;
import org.babyfish.jimmer.spring.graphql.DataFetchingEnvironments;
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.babyfish.jimmer.sql.example.model.BookStore;
import org.babyfish.jimmer.sql.example.repository.BookStoreRepository;
import org.babyfish.jimmer.sql.example.service.dto.BookStoreInput;
import org.babyfish.jimmer.sql.example.service.dto.CompositeBookStoreInput;
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
@Transactional
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
    public BookStore saveBookStore(
            @Argument BookStoreInput input,
            DataFetchingEnvironment env
    ) {
        return bookStoreRepository
                .save(input, DataFetchingEnvironments.createFetcher(BookStore.class, env))
                .getModifiedEntity();
    }

    @MutationMapping
    public BookStore createDeepBookStore(
            @Argument CompositeBookStoreInput input,
            DataFetchingEnvironment env
    ) {
        return bookStoreRepository
                .saveCommand(input)
                .setMode(SaveMode.INSERT_ONLY)
                .execute(DataFetchingEnvironments.createFetcher(BookStore.class, env))
                .getModifiedEntity();
    }

    @MutationMapping
    public BookStore updateDeepBookStoreById(
            @Argument long id,
            @Argument CompositeBookStoreInput input,
            DataFetchingEnvironment env
    ) {
        return bookStoreRepository
                .saveCommand(input.toEntityById(id))
                .setMode(SaveMode.UPDATE_ONLY)
                .execute(DataFetchingEnvironments.createFetcher(BookStore.class, env))
                .getModifiedEntity();
    }

    @MutationMapping
    public BookStore updateDeepBookStoreByKey(
            @Argument CompositeBookStoreInput input,
            DataFetchingEnvironment env
    ) {
        return bookStoreRepository
                .saveCommand(input)
                .setMode(SaveMode.UPDATE_ONLY)
                .execute(DataFetchingEnvironments.createFetcher(BookStore.class, env))
                .getModifiedEntity();
    }

    @MutationMapping
    public int deleteBookStore(@Argument long id) {
        bookStoreRepository.deleteById(id);
        // GraphQL requires return value,
        // but `deleteById` of spring-data does not support value.
        // Is there a better design?
        return 1;
    }
}
