package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.Specification;
import org.babyfish.jimmer.spring.repository.JRepository;
import org.babyfish.jimmer.sql.ast.Expression;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.query.Order;
import org.babyfish.jimmer.sql.ast.tuple.Tuple2;
import org.babyfish.jimmer.sql.example.model.Book;
import org.babyfish.jimmer.sql.example.model.BookTable;
import org.babyfish.jimmer.sql.example.model.Tables;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JRepository<Book, Long>, Tables {

    BookTable table = BOOK_TABLE;

    /**
     * Manually implement complex query.
     *
     * <p>The functionality of this method is the same as the super QBE method
     * {@link #find(Specification, Sort, Fetcher)}</p>
     */
    default List<Book> findBooks(
            @Nullable String name,
            @Nullable BigDecimal minPrice,
            @Nullable BigDecimal maxPrice,
            @Nullable String storeName,
            @Nullable String authorName,
            @Nullable String sortCode,
            Fetcher<Book> fetcher
    ) {
        return sql()
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
                .orderBy(Order.makeOrders(table, sortCode != null ? sortCode : "name asc"))
                .select(table.fetch(fetcher))
                .execute();
    }

    /**
     * Super QBE.
     *
     * <p>The functionality of this method is the same as the manual method
     * {@link #findBooks(String, BigDecimal, BigDecimal, String, String, String, Fetcher)}</p>
     */
    List<Book> find(
            Specification<Book> specification,
            Sort sort,
            Fetcher<Book> fetcher
    );

    default Map<Long, BigDecimal> findAvgPriceGroupByStoreId(Collection<Long> storeIds) {
        return Tuple2.toMap(
                sql()
                        .createQuery(table)
                        .where(table.storeId().in(storeIds))
                        .groupBy(table.storeId())
                        .select(
                                table.storeId(),
                                table.price().avg()
                        )
                        .execute()
        );
    }

    default Map<Long, List<Long>> findNewestIdsGroupByStoreId(Collection<Long> storeIds) {
        return Tuple2.toMultiMap(
                sql()
                        .createQuery(table)
                        .where(
                                Expression.tuple(table.storeId(), table.name(), table.edition()).in(
                                        sql().createSubQuery(table)
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
