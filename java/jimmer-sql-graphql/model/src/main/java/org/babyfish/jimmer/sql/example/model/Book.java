package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.babyfish.jimmer.sql.example.model.common.BaseEntity;
import org.babyfish.jimmer.sql.example.model.common.TenantAware;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
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
public interface Book extends BaseEntity, TenantAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    @Key // (1)
    String name();

    @Key // (2)
    int edition();

    BigDecimal price();

    @Nullable // (3) Null property, Java API requires this annotation, but kotlin API does not
    @ManyToOne // (4)
    @OnDissociate(DissociateAction.SET_NULL)
    BookStore store();

    @ManyToMany(orderedProps = { // (5)
            @OrderedProp("firstName"),
            @OrderedProp("lastName")
    })
    @JoinTable(
            name = "BOOK_AUTHOR_MAPPING",
            joinColumnName = "BOOK_ID",
            inverseJoinColumnName = "AUTHOR_ID"
    )
    List<Author> authors();

    // -----------------------------
    // Optional properties
    // -----------------------------

    // Optional property `storeId`
    // If this property is deleted, please add `BookInput.Mapper.toBookStore(Long)`
    @IdView  // (6)
    Long storeId();

    // Optional property `authorIds`
    // If this property is deleted, please add `BookInputMapper.toAuthor(Long)`
    @IdView("authors") // (7)
    List<Long> authorIds();
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/nullity
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one
(5) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(6) (7)https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/view/id-view
---------------------------------------------------*/
