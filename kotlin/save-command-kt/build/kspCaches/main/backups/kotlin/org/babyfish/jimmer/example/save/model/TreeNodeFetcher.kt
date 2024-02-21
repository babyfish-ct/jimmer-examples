@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = TreeNode::class)

package org.babyfish.jimmer.example.save.model

import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import org.babyfish.jimmer.`internal`.GeneratedBy
import org.babyfish.jimmer.kt.DslScope
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.fetcher.IdOnlyFetchType
import org.babyfish.jimmer.sql.fetcher.`impl`.FetcherImpl
import org.babyfish.jimmer.sql.kt.fetcher.FetcherCreator
import org.babyfish.jimmer.sql.kt.fetcher.KFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.KListFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.KRecursiveFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.KRecursiveListFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.`impl`.JavaFieldConfigUtils
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

public fun FetcherCreator<TreeNode>.`by`(block: TreeNodeFetcherDsl.() -> Unit): Fetcher<TreeNode> {
    val dsl = TreeNodeFetcherDsl(emptyTreeNodeFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

public fun FetcherCreator<TreeNode>.`by`(base: Fetcher<TreeNode>?,
        block: TreeNodeFetcherDsl.() -> Unit): Fetcher<TreeNode> {
    val dsl = TreeNodeFetcherDsl(base ?: emptyTreeNodeFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

@DslScope
public class TreeNodeFetcherDsl(
    fetcher: Fetcher<TreeNode>,
) {
    private var _fetcher: Fetcher<TreeNode> = fetcher

    public fun internallyGetFetcher(): Fetcher<TreeNode> = _fetcher

    public fun allScalarFields(): Unit {
        _fetcher = _fetcher.allScalarFields()
    }

    public fun allTableFields(): Unit {
        _fetcher = _fetcher.allTableFields()
    }

    public fun name(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("name")
        } else {
            _fetcher.remove("name")
        }
    }

    public fun parentNode(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("parentNode")
        } else {
            _fetcher.remove("parentNode")
        }
    }

    public fun parentNode(childFetcher: Fetcher<TreeNode>,
            cfgBlock: (KFieldDsl<TreeNode>.() -> Unit)? = null): Unit {
        _fetcher = _fetcher.add(
            "parentNode",
            childFetcher,
            JavaFieldConfigUtils.simple(cfgBlock)
        )
    }

    public fun parentNode(cfgBlock: (KFieldDsl<TreeNode>.() -> Unit)? = null,
            childBlock: TreeNodeFetcherDsl.() -> Unit): Unit {
        _fetcher = _fetcher.add(
            "parentNode",
            newFetcher(TreeNode::class).by(childBlock),
            JavaFieldConfigUtils.simple(cfgBlock)
        )
    }

    public fun parentNode(
        enabled: Boolean,
        childFetcher: Fetcher<TreeNode>,
        cfgBlock: (KFieldDsl<TreeNode>.() -> Unit)? = null,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("parentNode")
        } else {
            parentNode(childFetcher, cfgBlock)
        }
    }

    public fun parentNode(
        enabled: Boolean,
        cfgBlock: (KFieldDsl<TreeNode>.() -> Unit)? = null,
        childBlock: TreeNodeFetcherDsl.() -> Unit,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("parentNode")
        } else {
            parentNode(cfgBlock, childBlock)
        }
    }

    public fun `parentNode*`(cfgBlock: (KRecursiveFieldDsl<TreeNode>.() -> Unit)? = null): Unit {
        _fetcher = _fetcher.addRecursion(
            "parentNode",
            JavaFieldConfigUtils.recursive(cfgBlock)
        )
    }

    public fun parentNode(idOnlyFetchType: IdOnlyFetchType): Unit {
        _fetcher = _fetcher.add("parentNode", idOnlyFetchType)
    }

    public fun childNodes(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("childNodes")
        } else {
            _fetcher.remove("childNodes")
        }
    }

    public fun childNodes(childFetcher: Fetcher<TreeNode>,
            cfgBlock: (KListFieldDsl<TreeNode>.() -> Unit)? = null): Unit {
        _fetcher = _fetcher.add(
            "childNodes",
            childFetcher,
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun childNodes(cfgBlock: (KListFieldDsl<TreeNode>.() -> Unit)? = null,
            childBlock: TreeNodeFetcherDsl.() -> Unit): Unit {
        _fetcher = _fetcher.add(
            "childNodes",
            newFetcher(TreeNode::class).by(childBlock),
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun childNodes(
        enabled: Boolean,
        childFetcher: Fetcher<TreeNode>,
        cfgBlock: (KListFieldDsl<TreeNode>.() -> Unit)? = null,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("childNodes")
        } else {
            childNodes(childFetcher, cfgBlock)
        }
    }

    public fun childNodes(
        enabled: Boolean,
        cfgBlock: (KListFieldDsl<TreeNode>.() -> Unit)? = null,
        childBlock: TreeNodeFetcherDsl.() -> Unit,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("childNodes")
        } else {
            childNodes(cfgBlock, childBlock)
        }
    }

    public fun `childNodes*`(cfgBlock: (KRecursiveListFieldDsl<TreeNode>.() -> Unit)? = null):
            Unit {
        _fetcher = _fetcher.addRecursion(
            "childNodes",
            JavaFieldConfigUtils.recursiveList(cfgBlock)
        )
    }
}

private val emptyTreeNodeFetcher: Fetcher<TreeNode> = FetcherImpl(TreeNode::class.java)
