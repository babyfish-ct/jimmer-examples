package org.babyfish.jimmer.example.cloud.store;

import org.babyfish.jimmer.client.FetchBy;
import org.babyfish.jimmer.example.cloud.model.AuthorFetcher;
import org.babyfish.jimmer.example.cloud.model.BookFetcher;
import org.babyfish.jimmer.example.cloud.model.BookStore;
import org.babyfish.jimmer.example.cloud.model.BookStoreFetcher;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Load BookStore form current service
     * @param id The id of BookStore
     * @return The BookStore Object
     */
    @GetMapping("/store/{id}")
    public @FetchBy("SIMPLE_FETCHER") BookStore findStore(
            @PathVariable("id") long id
    ) {
        return storeRepository.findNullable(id, SIMPLE_FETCHER);
    }

    /**
     * Load BookStore form current service and associated objects from other services
     * @param id The id of BookStore
     * @return The BookStore object and associated objects
     */
    @GetMapping("/store/{id}/detail")
    public @FetchBy("COMPLEX_FETCHER") BookStore findStoreDetail(
            @PathVariable("id") long id
    ) {
        return storeRepository.findNullable(id, COMPLEX_FETCHER);
    }

    public static final Fetcher<BookStore> SIMPLE_FETCHER =
            BookStoreFetcher.$
                    .name();

    public static final Fetcher<BookStore> COMPLEX_FETCHER =
            BookStoreFetcher.$
                    .allScalarFields()
                    .books(
                            BookFetcher.$
                                    .allScalarFields()
                                    .authors(
                                            AuthorFetcher.$
                                                    .allScalarFields()
                                    )
                    );
}
