package org.babyfish.jimmer.sql.example.runtime.resolver;

import org.babyfish.jimmer.lang.Ref;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.TransientResolver;
import org.babyfish.jimmer.sql.event.AssociationEvent;
import org.babyfish.jimmer.sql.event.EntityEvent;
import org.babyfish.jimmer.sql.example.model.Book;
import org.babyfish.jimmer.sql.example.repository.BookRepository;
import org.babyfish.jimmer.sql.example.model.BookProps;
import org.babyfish.jimmer.sql.example.model.BookStore;
import org.babyfish.jimmer.sql.example.model.BookStoreProps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookStoreNewestBooksResolver implements TransientResolver<Long, List<Long>> { // (1)

    private final JSqlClient sql;

    private final BookRepository bookRepository;

    public BookStoreNewestBooksResolver(JSqlClient sql, BookRepository bookRepository) {
        this.sql = sql;
        this.bookRepository = bookRepository;
    }

    @Override
    public Map<Long, List<Long>> resolve(Collection<Long> ids) { // (2)
        return bookRepository.findNewestIdsGroupByStoreId(ids);
    }

    @Override
    public List<Long> getDefaultValue() { // (3)
        return Collections.emptyList();
    }

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
    @Override
    public Ref<SortedMap<String, Object>> getParameterMapRef() { // (4)
        return sql.getFilters().getTargetParameterMapRef(BookStoreProps.BOOKS);
    }

    // When a one-to-many association `BookStore.books` is modified
    // (for some records in the BOOK table, whether by modifying the foreign key field `STORE_ID` or
    // the field `TENANT` that the `TenantFilter` cares about),
    // the cache of the calculated property `BookStore.newestBooks` should be invalidated.
    @Nullable
    @Override
    public Collection<?> getAffectedSourceIds(@NotNull AssociationEvent e) { // (5)
        if (sql.getCaches().isAffectedBy(e) && e.getImmutableProp() == BookStoreProps.BOOKS.unwrap()) {
            return Collections.singletonList(e.getSourceId());
        }
        return null;
    }

    // Given that the foreign key `STORE_ID` of the current `Book` is not null and has not been modified,
    // if the `edition` of the current `Book` changes, the cache of the computed property `BookStore.newestBooks`
    // corresponding to `STORE_ID` should be invalidated.
    @Nullable
    @Override
    public Collection<?> getAffectedSourceIds(@NotNull EntityEvent<?> e) { // (6)
        if (sql.getCaches().isAffectedBy(e) &&
                !e.isEvict() &&
                e.getImmutableType().getJavaClass() == Book.class) {

            Ref<BookStore> storeRef = e.getUnchangedRef(BookProps.STORE);
            if (storeRef != null && storeRef.getValue() != null && e.isChanged(BookProps.EDITION)) {
                return Collections.singletonList(storeRef.getValue().id());
            }
        }
        return null;
    }
}

/*----------------Documentation Links----------------
(1) (2) (3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/transient#associative-calculation-bookstorenewestbooks
(4)) https://babyfish-ct.github.io/jimmer-doc/docs/cache/multiview-cache/user-filter#subkey-of-calculated-properties
(5) (6) https://babyfish-ct.github.io/jimmer-doc/docs/cache/multiview-cache/user-filter#consistency
---------------------------------------------------*/
