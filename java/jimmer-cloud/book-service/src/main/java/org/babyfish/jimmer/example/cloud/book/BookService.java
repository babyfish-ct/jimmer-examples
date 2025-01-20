package org.babyfish.jimmer.example.cloud.book;

import org.babyfish.jimmer.client.FetchBy;
import org.babyfish.jimmer.example.cloud.model.AuthorFetcher;
import org.babyfish.jimmer.example.cloud.model.Book;
import org.babyfish.jimmer.example.cloud.model.BookFetcher;
import org.babyfish.jimmer.example.cloud.model.BookStoreFetcher;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Load Book form current service
     * @param id The id of Book
     * @return The Book Object
     */
    @GetMapping("/book/{id}")
    public @FetchBy("SIMPLE_BOOK") Book findBook(
            @PathVariable("id") long id
    ) {
        return bookRepository.findNullable(id, SIMPLE_BOOK);
    }

    /**
     * Load Book form current service and associated objects from other services
     * @param id The id of Book
     * @return The Book object and associated objects
     */
    @GetMapping("/book/{id}/detail")
    public @FetchBy("COMPLEX_BOOK") Book findBookDetail(
            @PathVariable("id") long id
    ) {
        return bookRepository.findNullable(id, COMPLEX_BOOK);
    }

    public static final Fetcher<Book> SIMPLE_BOOK =
            BookFetcher.$
                    .name()
                    .edition();

    public static final Fetcher<Book> COMPLEX_BOOK =
            BookFetcher.$
                    .allScalarFields()
                    .store(
                            BookStoreFetcher.$
                                    .allScalarFields()
                    )
                    .authors(
                            AuthorFetcher.$
                                    .allScalarFields()
                    );
}
