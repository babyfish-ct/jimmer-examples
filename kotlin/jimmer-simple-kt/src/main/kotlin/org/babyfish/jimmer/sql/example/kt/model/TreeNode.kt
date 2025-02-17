package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*

@Entity
@KeyUniqueConstraint
interface TreeNode {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     * The name of current TreeNode.
     *
     * This property forms a unique constraint,
     * which is `Key` of Jimmer
     */
    @Key
    val name: String

    /**
     * The parent node of the current TreeNode.
     *
     * The type of the associated object is the
     * same as the current object type, so it
     * is a self-associated property that can
     * be queried recursively.
     */
    @Key
    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    val parent: TreeNode?

    /**
     * The child nodes of the current TreeNode.
     *
     * The type of the associated object is the
     * same as the current object type, so it
     * is a self-associated property that can
     * be queried recursively.
     */
    @OneToMany(mappedBy = "parent", orderedProps = [OrderedProp("name")])
    val childNodes: List<TreeNode>
}