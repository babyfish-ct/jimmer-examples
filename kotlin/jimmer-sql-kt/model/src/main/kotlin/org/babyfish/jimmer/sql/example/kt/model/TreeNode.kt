package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.example.kt.model.common.BaseEntity

@Entity
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
     * The name of current Book.
     *
     * This property forms a unique constraint together with
     * the [parent] property, which is `@Key` of Jimmer
     */
    @Key // (1)
    val name: String

    /**
     * The parent of current Book.
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