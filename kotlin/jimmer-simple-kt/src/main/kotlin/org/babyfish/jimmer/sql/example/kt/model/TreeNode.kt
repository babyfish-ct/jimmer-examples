package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*

@Entity
@KeyUniqueConstraint
interface TreeNode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key
    val name: String

    @Key
    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    val parent: TreeNode?

    @OneToMany(mappedBy = "parent", orderedProps = [OrderedProp("name")])
    val childNodes: List<TreeNode>
}