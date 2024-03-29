package org.babyfish.jimmer.example.kt.core.model

import org.babyfish.jimmer.Immutable
import org.babyfish.jimmer.kt.ImmutableCompanion

@Immutable
interface TreeNode {

    val name: String

    val parent: TreeNode?

    val childNodes: List<TreeNode>

    /**
     * The users naturally choose whether to declare this companion object.
     *
     * <p>It makes it easier for you to build immutable objects, like this</p>
     * <ul>
     *     <li>Create immutable object from scratch
     * <pre><code>
     * val treeNode = TreeNode {
     *     name = "Home"
     *     childNodes = listOf(
     *          TreeNode{ name = "Foods" },
     *          TreeNode{ name = "Drinks" },
     *     )
     * }
     * </code></pre>
     *     </li>
     *
     *      <li>Create immutable object from scratch
     * <pre><code>
     * val treeNode = TreeNode(oldTreeNode) {...Do sth...}</code></pre>
     *     </li>
     * </ul>
     */
    companion object: ImmutableCompanion<TreeNode>
}