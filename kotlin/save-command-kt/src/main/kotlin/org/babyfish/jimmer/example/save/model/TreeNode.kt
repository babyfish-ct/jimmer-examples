package org.babyfish.jimmer.example.save.model

import org.babyfish.jimmer.sql.*

@Entity
@KeyUniqueConstraint
interface TreeNode {

    @Id
    @Column(name = "node_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key
    val name: String

    @Key
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @OnDissociate(DissociateAction.DELETE)
    val parentNode: TreeNode?

    @OneToMany(mappedBy = "parentNode")
    val childNodes: List<TreeNode>
}