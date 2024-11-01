package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.BookStore;
import org.babyfish.jimmer.sql.example.model.BookStoreTable;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStoreRepository extends AbstractJavaRepository<BookStore, Long> {

    private final static BookStoreTable table = BookStoreTable.$;

    protected BookStoreRepository(JSqlClient sql) {
        super(sql);
    }

    public List<BookStore> findByNameLikeOrderByName(
            @Nullable String name,
            @Nullable Fetcher<BookStore> fetcher
            ) {
        return sql
                .createQuery(table)
                .where(table.name().ilikeIf(name))
                .orderBy(table.name())
                .select(table.fetch(fetcher))
                .execute();
    }
}
