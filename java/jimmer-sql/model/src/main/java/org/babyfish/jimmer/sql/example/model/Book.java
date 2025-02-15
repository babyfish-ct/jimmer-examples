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

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of current Book.
     *
     * <p>This property forms a unique constraint together with
     * the {@code edition} property, which is {@code @Key} of Jimmer</p>
     */
    @Key // (1)
    String name();

    /**
     * The edition of current Book.
     *
     * <p>This property forms a unique constraint together with
     * the {@code name} property, which is {@code @Key} of Jimmer</p>
     */
    @Key // (2)
    int edition();

    BigDecimal price();

    /**
     * The bookstore to which the current book belongs,
     * representing a many-to-one association
     */
    @Nullable // (3) Null property, Java API requires this annotation, but kotlin API does not
    @ManyToOne // (4)
    @OnDissociate(DissociateAction.SET_NULL)
    BookStore store();

    /**
     * All authors who participated in writing
     * the current book,
     * representing a many-to-many association
     */
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

    /**
     * Optional id-view property `storeId`
     *
     * <p>For reference association, even if this id-view property is not declared,
     * the SQL DSL can still use the associated id property "storeId"</p>
     */
    @IdView  // (6)
    Long storeId();

    /**
     * Optional id-view property `authorIds`
     */
    @IdView("authors") // (7)
    List<Long> authorIds();
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/nullity
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one
(5) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(6) (7) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/view/id-view
---------------------------------------------------*/
