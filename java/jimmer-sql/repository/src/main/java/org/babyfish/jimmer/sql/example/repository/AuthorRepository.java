package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.Specification;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.spring.repository.SpringOrders;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.Author;
import org.babyfish.jimmer.sql.example.model.AuthorTable;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository extends AbstractJavaRepository<Author, Long> {

    private static final AuthorTable table = AuthorTable.$;

    public AuthorRepository(JSqlClient sql) {
        super(sql);
    }

    public List<Author> find(
            Specification<Author> specification,
            Sort sort,
            Fetcher<Author> fetcher
    ) {
        return sql
                .createQuery(table)
                .where(specification)
                .orderBy(SpringOrders.toOrders(table, sort))
                .select(table.fetch(fetcher))
                .execute();
    }
}
