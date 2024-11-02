package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.Specification;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.spring.repository.SpringOrders;
import org.babyfish.jimmer.spring.repository.support.SpringPageFactory;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Expression;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.tuple.Tuple2;
import org.babyfish.jimmer.sql.example.model.Book;
import org.babyfish.jimmer.sql.example.model.BookTable;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository extends AbstractJavaRepository<Book, Long> {

    private static final BookTable table = BookTable.$;

    public BookRepository(JSqlClient sql) {
        super(sql);
    }

    /**
     * Manually implement complex query.
     *
     * <p>The functionality of this method is the same as the super QBE method
     * {@link #find(Pageable, Specification, Fetcher)}</p>
     */
    public Page<Book> findBooks(
            Pageable pageable,
            @Nullable String name,
            @Nullable BigDecimal minPrice,
            @Nullable BigDecimal maxPrice,
            @Nullable String storeName,
            @Nullable String authorName,
            @Nullable Fetcher<Book> fetcher
    ) {
        return sql
                .createQuery(table)
                .where(table.name().ilikeIf(name))
                .where(table.price().betweenIf(minPrice, maxPrice))
                .where(table.store().name().ilikeIf(storeName))
                .where(
                        table.authors(author ->
                                Predicate.or(
                                        author.firstName().ilikeIf(authorName),
                                        author.lastName().ilikeIf(authorName)
                                )
                        )
                )
                .orderBy(SpringOrders.toOrders(table, pageable.getSort()))
                .select(table.fetch(fetcher))
                .fetchPage(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        SpringPageFactory.getInstance()
                );
    }

    /**
     * Super QBE.
     *
     * <p>The functionality of this method is the same as the manual method
     * {@link #findBooks(Pageable, String, BigDecimal, BigDecimal, String, String, Fetcher)}</p>
     */
    public Page<Book> find(
            Pageable pageable,
            Specification<Book> specification,
            @Nullable Fetcher<Book> fetcher
    ) {
        return sql
                .createQuery(table)
                .where(specification)
                .select(table.fetch(fetcher))
                .fetchPage(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        SpringPageFactory.getInstance()
                );
    }

    public Map<Long, BigDecimal> findAvgPriceGroupByStoreId(Collection<Long> storeIds) {
        return Tuple2.toMap(
                sql
                        .createQuery(table)
                        .where(table.storeId().in(storeIds))
                        .groupBy(table.storeId())
                        .select(
                                table.storeId(),
                                table.price().avgAsDecimal()
                        )
                        .execute()
        );
    }

    public Map<Long, List<Long>> findNewestIdsGroupByStoreId(Collection<Long> storeIds) {
        return Tuple2.toMultiMap(
                sql
                        .createQuery(table)
                        .where(
                                Expression.tuple(table.storeId(), table.name(), table.edition()).in(
                                        sql
                                                .createSubQuery(table)
                                                // Apply root predicate to sub query is faster here.
                                                .where(table.storeId().in(storeIds))
                                                .groupBy(table.storeId(), table.name())
                                                .select(
                                                        table.storeId(),
                                                        table.name(),
                                                        table.edition().max()
                                                )
                                )
                        )
                        .select(
                                table.storeId(),
                                table.id()
                        )
                        .execute()
        );
    }
}

