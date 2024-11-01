package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.spring.repository.JRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.BookStore;
import org.springframework.stereotype.Component;

@Component
public class BookStoreRepository extends AbstractJavaRepository<BookStore, Long> {
    public BookStoreRepository(JSqlClient sql) {
        super(sql);
    }
}

