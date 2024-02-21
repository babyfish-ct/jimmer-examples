@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = TreeNode::class)

package org.babyfish.jimmer.example.save.model

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import org.babyfish.jimmer.`internal`.GeneratedBy
import org.babyfish.jimmer.kt.toImmutableProp
import org.babyfish.jimmer.meta.TypedProp
import org.babyfish.jimmer.sql.ast.Selection
import org.babyfish.jimmer.sql.kt.ast.expression.KNonNullExpression
import org.babyfish.jimmer.sql.kt.ast.expression.KNonNullPropExpression
import org.babyfish.jimmer.sql.kt.ast.expression.KNullablePropExpression
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullProps
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTable
import org.babyfish.jimmer.sql.kt.ast.table.KNonNullTableEx
import org.babyfish.jimmer.sql.kt.ast.table.KNullableProps
import org.babyfish.jimmer.sql.kt.ast.table.KNullableTable
import org.babyfish.jimmer.sql.kt.ast.table.KNullableTableEx
import org.babyfish.jimmer.sql.kt.ast.table.KProps
import org.babyfish.jimmer.sql.kt.ast.table.KRemoteRef
import org.babyfish.jimmer.sql.kt.ast.table.KTableEx
import org.babyfish.jimmer.sql.kt.ast.table.`impl`.KRemoteRefImplementor
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

public val KNonNullProps<TreeNode>.id: KNonNullPropExpression<Long>
    get() = get<Long>(TreeNodeProps.ID.unwrap()) as KNonNullPropExpression<Long>

public val KNullableProps<TreeNode>.id: KNullablePropExpression<Long>
    get() = get<Long>(TreeNodeProps.ID.unwrap()) as KNullablePropExpression<Long>

public val KNonNullProps<TreeNode>.name: KNonNullPropExpression<String>
    get() = get<String>(TreeNodeProps.NAME.unwrap()) as KNonNullPropExpression<String>

public val KNullableProps<TreeNode>.name: KNullablePropExpression<String>
    get() = get<String>(TreeNodeProps.NAME.unwrap()) as KNullablePropExpression<String>

public val KProps<TreeNode>.parentNode: KNonNullTable<TreeNode>
    get() = join(TreeNodeProps.PARENT_NODE.unwrap())

public val KProps<TreeNode>.`parentNode?`: KNullableTable<TreeNode>
    get() = outerJoin(TreeNodeProps.PARENT_NODE.unwrap())

public val KTableEx<TreeNode>.parentNode: KNonNullTableEx<TreeNode>
    get() = join(TreeNodeProps.PARENT_NODE.unwrap())

public val KTableEx<TreeNode>.`parentNode?`: KNullableTableEx<TreeNode>
    get() = outerJoin(TreeNodeProps.PARENT_NODE.unwrap())

public val KProps<TreeNode>.parentNodeId: KNullablePropExpression<Long>
    get() = getAssociatedId<Long>(TreeNodeProps.PARENT_NODE.unwrap()) as
            KNullablePropExpression<Long>

public
        fun KProps<TreeNode>.childNodes(block: KNonNullTableEx<TreeNode>.() -> KNonNullExpression<Boolean>?):
        KNonNullExpression<Boolean>? = exists(TreeNodeProps.CHILD_NODES.unwrap(), block)

public val KTableEx<TreeNode>.childNodes: KNonNullTableEx<TreeNode>
    get() = join(TreeNodeProps.CHILD_NODES.unwrap())

public val KTableEx<TreeNode>.`childNodes?`: KNullableTableEx<TreeNode>
    get() = outerJoin(TreeNodeProps.CHILD_NODES.unwrap())

public val KRemoteRef.NonNull<TreeNode>.id: KNonNullPropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNonNullPropExpression<Long>

public val KRemoteRef.Nullable<TreeNode>.id: KNullablePropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNullablePropExpression<Long>

public fun KNonNullTable<TreeNode>.fetchBy(block: TreeNodeFetcherDsl.() -> Unit):
        Selection<TreeNode> = fetch(newFetcher(TreeNode::class).`by`(block))

public fun KNullableTable<TreeNode>.fetchBy(block: TreeNodeFetcherDsl.() -> Unit):
        Selection<TreeNode?> = fetch(newFetcher(TreeNode::class).`by`(block))

public object TreeNodeProps {
    public val ID: TypedProp.Scalar<TreeNode, Long> =
            TypedProp.scalar(TreeNode::id.toImmutableProp())

    public val NAME: TypedProp.Scalar<TreeNode, String> =
            TypedProp.scalar(TreeNode::name.toImmutableProp())

    public val PARENT_NODE: TypedProp.Reference<TreeNode, TreeNode?> =
            TypedProp.reference(TreeNode::parentNode.toImmutableProp())

    public val CHILD_NODES: TypedProp.ReferenceList<TreeNode, TreeNode> =
            TypedProp.referenceList(TreeNode::childNodes.toImmutableProp())
}
