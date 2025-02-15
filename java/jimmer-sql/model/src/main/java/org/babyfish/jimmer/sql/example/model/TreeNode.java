package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.Key;

import org.babyfish.jimmer.sql.*;
import org.babyfish.jimmer.sql.example.model.common.BaseEntity;
import org.jetbrains.annotations.Nullable;

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
public interface TreeNode extends BaseEntity {

    @Id
    @Column(name = "NODE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of current TreeNode.
     *
     * <p>This property forms a unique constraint together with
     * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
     */
    @Key // (1)
    String name();

    /**
     * The parent of current TreeNode.
     *
     * <p>This property forms a unique constraint together with
     * the {@link #name()} property, which is {@code @Key} of Jimmer</p>
     */
    @Nullable // (2) Null property, Java API requires this annotation, but kotlin API does not
    @Key // (3)
    @ManyToOne // (4)
    @OnDissociate(DissociateAction.DELETE) // (5)
    TreeNode parent();

    @OneToMany(mappedBy = "parent", orderedProps = @OrderedProp("name")) // (6)
    List<TreeNode> childNodes();
}

/*----------------Documentation Links----------------
(1) (3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/nullity
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one

(5) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/on-dissociate
  https://babyfish-ct.github.io/jimmer-doc/docs/mutation/save-command/dissociation
  https://babyfish-ct.github.io/jimmer-doc/docs/mutation/delete-command

(6) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/one-to-many
---------------------------------------------------*/
