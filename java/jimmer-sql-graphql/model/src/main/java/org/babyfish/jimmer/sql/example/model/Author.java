package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.Formula;
import org.babyfish.jimmer.sql.*;
import org.babyfish.jimmer.sql.example.model.common.BaseEntity;

import java.util.List;

/*
 * In this example, `@KeyConstraint` will not take effect,
 * meaning it won't utilize the database's upsert capability.
 * Instead, it will use a select query to determine whether
 * the subsequent operation should be an insert or update.
 *
 * This is due to:
 *
 * 1. For the root object being saved, the use of
 *    `DraftInterceptor` will trigger a query
 *
 * 2. For the associated child objects being saved, in
 *    addition to reason #1, there's also the fact that
 *    by default, the `Transferable` capability of child
 *    objects is not enabled. This means that by default,
 *    a parent object cannot take child objects from
 *    other parent objects.
 *
 * However, in actual projects, it is still recommended
 * to specify `@KeyConstraint` for each entity.
 */
@Entity
@KeyUniqueConstraint(
        // Only for mysql
        noMoreUniqueConstraints = true,
        // Only for postgres
        isNullNotDistinct = true
)
public interface Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    // It is inappropriate to use `firstName` and `lastName`
    // as keys in actual project, but this is just a small demo.

    @Key // (1)
    String firstName();

    @Key // (2)
    String lastName();

    Gender gender();

    @ManyToMany(mappedBy = "authors", orderedProps = { // (3)
            @OrderedProp("name"),
            @OrderedProp(value = "edition", desc = true)
    })
    List<Book> books();

    // -----------------------------
    //
    // Everything below this line are calculated properties.
    //
    // The simple calculated properties are shown here. As for the
    // complex calculated properties, you can view `BookStore.avgPrice` and
    // `BookStore.newestBooks`
    // -----------------------------

    @Formula(dependencies = {"firstName", "lastName"}) // (4)
    default String fullName() {
        return firstName() + ' ' + lastName();
    }

    // The simple property above is simple calculation based on JAVA expression,
    // you can also define simple calculations given SQL expressions like this
    //
    // @Formula(sql = "concat(%alias.FIRST_NAME, ' ', %alias.LAST_NAME)")
    // String fullName();
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/formula
---------------------------------------------------*/
