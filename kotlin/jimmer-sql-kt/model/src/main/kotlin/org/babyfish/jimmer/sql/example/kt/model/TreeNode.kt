package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.example.kt.model.common.BaseEntity

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
interface TreeNode : BaseEntity {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @Column(name = "NODE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     * The name of current TreeNode.
     *
     * This property forms a unique constraint together with
     * the [parent] property, which is `@Key` of Jimmer
     */
    @Key // (1)
    val name: String

    /**
     * The parent of current TreeNode.
     *
     * This property forms a unique constraint together with
     * the [name] property, which is `@Key` of Jimmer
     */
    @Key // (2)
    @ManyToOne // (3)
    @OnDissociate(DissociateAction.DELETE) // (4)
    val parent: TreeNode?

    @OneToMany(mappedBy = "parent", orderedProps = [OrderedProp("name")]) // (5)
    val childNodes: List<TreeNode>
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one

(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/on-dissociate
  https://babyfish-ct.github.io/jimmer-doc/docs/mutation/save-command/dissociation
  https://babyfish-ct.github.io/jimmer-doc/docs/mutation/delete-command

(5) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/one-to-many
---------------------------------------------------*/