package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.example.model.Author;
import org.babyfish.jimmer.sql.example.model.AuthorTable;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository extends AbstractJavaRepository<Author, Long> {

    private static final AuthorTable table = AuthorTable.$;

    protected AuthorRepository(JSqlClient sql) {
        super(sql);
    }

    public List<Author> findByName(String name, Fetcher<Author> fetcher) {
        return sql
                .createQuery(table)
                .whereIf(
                        name != null,
                        () -> Predicate.or(
                                table.firstName().eq(name),
                                table.lastName().eq(name)
                        )
                )
                .select(table.fetch(fetcher))
                .execute();
    }
}
