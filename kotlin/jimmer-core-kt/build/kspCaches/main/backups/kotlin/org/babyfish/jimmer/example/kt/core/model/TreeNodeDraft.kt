@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)

package org.babyfish.jimmer.example.kt.core.model

import com.fasterxml.jackson.`annotation`.JsonIgnore
import java.io.Serializable
import java.lang.System
import kotlin.Any
import kotlin.Boolean
import kotlin.Cloneable
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import org.babyfish.jimmer.CircularReferenceException
import org.babyfish.jimmer.Draft
import org.babyfish.jimmer.DraftConsumer
import org.babyfish.jimmer.ImmutableObjects
import org.babyfish.jimmer.UnloadedException
import org.babyfish.jimmer.`internal`.GeneratedBy
import org.babyfish.jimmer.jackson.ImmutableModuleRequiredException
import org.babyfish.jimmer.kt.DslScope
import org.babyfish.jimmer.kt.ImmutableCreator
import org.babyfish.jimmer.meta.ImmutablePropCategory
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.meta.PropId
import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.runtime.ImmutableSpi
import org.babyfish.jimmer.runtime.Internal
import org.babyfish.jimmer.runtime.NonSharedList
import org.babyfish.jimmer.runtime.Visibility

@DslScope
@GeneratedBy(type = TreeNode::class)
public interface TreeNodeDraft : TreeNode, Draft {
    public override var name: String

    public override var parent: TreeNode?

    public override var childNodes: List<TreeNode>

    public fun parent(): TreeNodeDraft

    public fun childNodes(): MutableList<TreeNodeDraft>

    public object `$` {
        public const val SLOT_NAME: Int = 0

        public const val SLOT_PARENT: Int = 1

        public const val SLOT_CHILD_NODES: Int = 2

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                TreeNode::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as TreeNode?)
            }
            .add(SLOT_NAME, "name", ImmutablePropCategory.SCALAR, String::class.java, false)
            .add(SLOT_PARENT, "parent", ImmutablePropCategory.REFERENCE, TreeNode::class.java, true)
            .add(SLOT_CHILD_NODES, "childNodes", ImmutablePropCategory.REFERENCE_LIST,
                    TreeNode::class.java, false)
            .build()

        public fun produce(base: TreeNode? = null, block: TreeNodeDraft.() -> Unit): TreeNode {
            val consumer = DraftConsumer<TreeNodeDraft> { block(it) }
            return Internal.produce(type, base, consumer) as TreeNode
        }

        private abstract interface Implementor : TreeNode, ImmutableSpi {
            public val dummyPropForJacksonError__: Int
                get() = throw ImmutableModuleRequiredException()

            public override fun __get(prop: PropId): Any? = when (prop.asIndex()) {
                -1 ->
                	__get(prop.asName())
                SLOT_NAME ->
                	name
                SLOT_PARENT ->
                	parent
                SLOT_CHILD_NODES ->
                	childNodes
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "name" ->
                	name
                "parent" ->
                	parent
                "childNodes" ->
                	childNodes
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __type(): ImmutableType = `$`.type
        }

        private class Impl : Implementor, Cloneable, Serializable {
            @get:JsonIgnore
            internal var __visibility: Visibility? = null

            internal var __nameValue: String? = null

            internal var __parentValue: TreeNode? = null

            internal var __parentLoaded: Boolean = false

            internal var __childNodesValue: NonSharedList<TreeNode>? = null

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(TreeNode::class.java, "name")
                    }
                    return __nameValue
                }

            public override val parent: TreeNode?
                @JsonIgnore
                get() {
                    if (!__parentLoaded) {
                        throw UnloadedException(TreeNode::class.java, "parent")
                    }
                    return __parentValue
                }

            public override val childNodes: List<TreeNode>
                @JsonIgnore
                get() {
                    val __childNodesValue = this.__childNodesValue
                    if (__childNodesValue === null) {
                        throw UnloadedException(TreeNode::class.java, "childNodes")
                    }
                    return __childNodesValue
                }

            public override fun clone(): Impl = super.clone() as Impl

            public override fun __isLoaded(prop: PropId): Boolean = when (prop.asIndex()) {
                -1 ->
                	__isLoaded(prop.asName())
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_PARENT ->
                	__parentLoaded
                SLOT_CHILD_NODES ->
                	__childNodesValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "name" ->
                	__nameValue !== null
                "parent" ->
                	__parentLoaded
                "childNodes" ->
                	__childNodesValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __isVisible(prop: PropId): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop.asIndex()) {
                    -1 ->
                    	__isVisible(prop.asName())
                    SLOT_NAME ->
                    	__visibility.visible(SLOT_NAME)
                    SLOT_PARENT ->
                    	__visibility.visible(SLOT_PARENT)
                    SLOT_CHILD_NODES ->
                    	__visibility.visible(SLOT_CHILD_NODES)
                    else -> true
                }
            }

            public override fun __isVisible(prop: String): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop) {
                    "name" ->
                    	__visibility.visible(SLOT_NAME)
                    "parent" ->
                    	__visibility.visible(SLOT_PARENT)
                    "childNodes" ->
                    	__visibility.visible(SLOT_CHILD_NODES)
                    else -> true
                }
            }

            public fun __shallowHashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__parentLoaded) {
                    hash = 31 * hash + System.identityHashCode(__parentValue)
                }
                if (__childNodesValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__childNodesValue)
                }
                return hash
            }

            public override fun hashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__parentLoaded) {
                    hash = 31 * hash + (__parentValue?.hashCode() ?: 0)
                }
                if (__childNodesValue !== null) {
                    hash = 31 * hash + __childNodesValue.hashCode()
                }
                return hash
            }

            public override fun __hashCode(shallow: Boolean): Int = if (shallow) __shallowHashCode()
                    else hashCode()

            public fun __shallowEquals(other: Any?): Boolean {
                val __other = other as? Implementor
                if (__other === null) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_NAME))) {
                    return false
                }
                val __nameLoaded = 
                    this.__nameValue !== null
                if (__nameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_NAME)))) {
                    return false
                }
                if (__nameLoaded && this.__nameValue != __other.name) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PARENT)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PARENT))) {
                    return false
                }
                val __parentLoaded = 
                    this.__parentLoaded
                if (__parentLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PARENT)))) {
                    return false
                }
                if (__parentLoaded && this.__parentValue !== __other.parent) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_CHILD_NODES)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_CHILD_NODES))) {
                    return false
                }
                val __childNodesLoaded = 
                    this.__childNodesValue !== null
                if (__childNodesLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_CHILD_NODES)))) {
                    return false
                }
                if (__childNodesLoaded && this.__childNodesValue !== __other.childNodes) {
                    return false
                }
                return true
            }

            public override fun equals(other: Any?): Boolean {
                val __other = other as? Implementor
                if (__other === null) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_NAME))) {
                    return false
                }
                val __nameLoaded = 
                    this.__nameValue !== null
                if (__nameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_NAME)))) {
                    return false
                }
                if (__nameLoaded && this.__nameValue != __other.name) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PARENT)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PARENT))) {
                    return false
                }
                val __parentLoaded = 
                    this.__parentLoaded
                if (__parentLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PARENT)))) {
                    return false
                }
                if (__parentLoaded && this.__parentValue != __other.parent) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_CHILD_NODES)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_CHILD_NODES))) {
                    return false
                }
                val __childNodesLoaded = 
                    this.__childNodesValue !== null
                if (__childNodesLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_CHILD_NODES)))) {
                    return false
                }
                if (__childNodesLoaded && this.__childNodesValue != __other.childNodes) {
                    return false
                }
                return true
            }

            public override fun __equals(obj: Any?, shallow: Boolean): Boolean = if (shallow)
                    __shallowEquals(obj) else equals(obj)

            public override fun toString(): String = ImmutableObjects.toString(this)
        }

        private class DraftImpl(
            ctx: DraftContext,
            base: TreeNode?,
        ) : Implementor, TreeNodeDraft, DraftSpi {
            private val __ctx: DraftContext = ctx

            private val __base: Impl? = base as Impl?

            private var __modified: Impl? = if (base === null) Impl() else null

            public var __resolving: Boolean = false

            public override var name: String
                @JsonIgnore
                get() = (__modified ?: __base!!).name
                set(name) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__nameValue = name
                }

            public override var parent: TreeNode?
                @JsonIgnore
                get() = __ctx.toDraftObject((__modified ?: __base!!).parent)
                set(parent) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__parentValue = parent
                    __tmpModified.__parentLoaded = true
                }

            public override var childNodes: List<TreeNode>
                @JsonIgnore
                get() = __ctx.toDraftList((__modified ?: __base!!).childNodes, TreeNode::class.java,
                        true)
                set(childNodes) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__childNodesValue =
                            NonSharedList.of(__tmpModified.__childNodesValue, childNodes)
                }

            public override fun __isLoaded(prop: PropId): Boolean = (__modified ?:
                    __base!!).__isLoaded(prop)

            public override fun __isLoaded(prop: String): Boolean = (__modified ?:
                    __base!!).__isLoaded(prop)

            public override fun __isVisible(prop: PropId): Boolean = (__modified ?:
                    __base!!).__isVisible(prop)

            public override fun __isVisible(prop: String): Boolean = (__modified ?:
                    __base!!).__isVisible(prop)

            public override fun hashCode(): Int = (__modified ?: __base!!).hashCode()

            public override fun __hashCode(shallow: Boolean): Int = (__modified ?:
                    __base!!).__hashCode(shallow)

            public override fun equals(other: Any?): Boolean = (__modified ?:
                    __base!!).equals(other)

            public override fun __equals(other: Any?, shallow: Boolean): Boolean = (__modified ?:
                    __base!!).__equals(other, shallow)

            public override fun toString(): String = ImmutableObjects.toString((__modified ?:
                    __base!!))

            public override fun parent(): TreeNodeDraft {
                if (!__isLoaded(PropId.byIndex(SLOT_PARENT)) || parent === null) {
                    parent = `$`.produce {}
                }
                return parent as TreeNodeDraft
            }

            @Suppress("UNCHECKED_CAST")
            public override fun childNodes(): MutableList<TreeNodeDraft> {
                if (!__isLoaded(PropId.byIndex(SLOT_CHILD_NODES))) {
                    childNodes = emptyList()
                }
                return childNodes as MutableList<TreeNodeDraft>
            }

            public override fun __unload(prop: PropId): Unit {
                when (prop.asIndex()) {
                    -1 ->
                    	__unload(prop.asName())
                    SLOT_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    SLOT_PARENT ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__parentLoaded = false
                    SLOT_CHILD_NODES ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__childNodesValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __unload(prop: String): Unit {
                when (prop) {
                    "name" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    "parent" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__parentLoaded = false
                    "childNodes" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__childNodesValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: PropId, `value`: Any?): Unit {
                when (prop.asIndex()) {
                    -1 ->
                    	__set(prop.asName(), value)
                    SLOT_NAME ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    SLOT_PARENT ->
                    	this.parent = value as TreeNode?
                    SLOT_CHILD_NODES ->
                    	this.childNodes = value as List<TreeNode>?
                    	?: throw IllegalArgumentException("'childNodes cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: String, `value`: Any?): Unit {
                when (prop) {
                    "name" ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    "parent" ->
                    	this.parent = value as TreeNode?
                    "childNodes" ->
                    	this.childNodes = value as List<TreeNode>?
                    	?: throw IllegalArgumentException("'childNodes cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __show(prop: PropId, visible: Boolean): Unit {
                val __visibility = (__modified ?: __base!!).__visibility
                    ?: if (visible) {
                        null
                    } else {
                        Visibility.of(3).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop.asIndex()) {
                    -1 ->
                    	__show(prop.asName(), visible)
                    SLOT_NAME ->
                    	__visibility.show(SLOT_NAME, visible)
                    SLOT_PARENT ->
                    	__visibility.show(SLOT_PARENT, visible)
                    SLOT_CHILD_NODES ->
                    	__visibility.show(SLOT_CHILD_NODES, visible)
                    else -> throw IllegalArgumentException(
                        "Illegal property id: \"" + 
                        prop + 
                        "\",it does not exists"
                    )
                }
            }

            public override fun __show(prop: String, visible: Boolean): Unit {
                val __visibility = (__modified ?: __base!!).__visibility
                    ?: if (visible) {
                        null
                    } else {
                        Visibility.of(3).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "name" ->
                    	__visibility.show(SLOT_NAME, visible)
                    "parent" ->
                    	__visibility.show(SLOT_PARENT, visible)
                    "childNodes" ->
                    	__visibility.show(SLOT_CHILD_NODES, visible)
                    else -> throw IllegalArgumentException(
                        "Illegal property name: \"" + 
                        prop + 
                        "\",it does not exists"
                    )
                }
            }

            public override fun __draftContext(): DraftContext = __ctx

            public override fun __resolve(): Any {
                if (__resolving) {
                    throw CircularReferenceException()
                }
                __resolving = true
                try {
                    val base = __base
                    var __tmpModified = __modified
                    if (__tmpModified === null) {
                        if (__isLoaded(PropId.byIndex(SLOT_PARENT))) {
                            val oldValue = base!!.parent
                            val newValue = __ctx.resolveObject(oldValue)
                            if (oldValue !== newValue) {
                                parent = newValue
                            }
                        }
                        if (__isLoaded(PropId.byIndex(SLOT_CHILD_NODES))) {
                            val oldValue = base!!.childNodes
                            val newValue = __ctx.resolveList(oldValue)
                            if (oldValue !== newValue) {
                                childNodes = newValue
                            }
                        }
                        __tmpModified = __modified
                    } else {
                        __tmpModified.__parentValue =
                                __ctx.resolveObject(__tmpModified.__parentValue)
                        __tmpModified.__childNodesValue =
                                NonSharedList.of(__tmpModified.__childNodesValue,
                                __ctx.resolveList(__tmpModified.__childNodesValue))
                    }
                    if (base !== null && __tmpModified === null) {
                        return base
                    }
                    return __tmpModified!!
                } finally {
                    __resolving = false
                }
            }
        }
    }

    public class MapStruct {
        private var name: String? = null

        private var __parentLoaded: Boolean = false

        private var parent: TreeNode? = null

        private var childNodes: List<TreeNode>? = null

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun parent(parent: TreeNode?): MapStruct {
            this.__parentLoaded = true
            this.parent = parent
            return this
        }

        public fun childNodes(childNodes: List<TreeNode>?): MapStruct {
            this.childNodes = childNodes ?: emptyList()
            return this
        }

        public fun build(): TreeNode = TreeNodeDraft
        .`$`.produce {val __that = this@MapStruct
            __that.name?.let  {
                name = it
            }
            if (__that.__parentLoaded) {
                parent = __that.parent
            }
            __that.childNodes?.let  {
                childNodes = it
            }
        }
    }
}

public fun ImmutableCreator<TreeNode>.`by`(base: TreeNode? = null, block: TreeNodeDraft.() -> Unit):
        TreeNode = TreeNodeDraft.`$`.produce(base, block)

public fun MutableList<TreeNodeDraft>.addBy(base: TreeNode? = null,
        block: TreeNodeDraft.() -> Unit): MutableList<TreeNodeDraft> {
    add(TreeNodeDraft.`$`.produce(base, block) as TreeNodeDraft)
    return this
}

public fun TreeNode.copy(block: TreeNodeDraft.() -> Unit): TreeNode =
        TreeNodeDraft.`$`.produce(this, block)
