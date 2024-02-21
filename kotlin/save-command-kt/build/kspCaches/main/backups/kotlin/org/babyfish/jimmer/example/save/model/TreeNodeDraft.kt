@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)

package org.babyfish.jimmer.example.save.model

import com.fasterxml.jackson.`annotation`.JsonIgnore
import java.io.Serializable
import java.lang.System
import kotlin.Any
import kotlin.Boolean
import kotlin.Cloneable
import kotlin.Int
import kotlin.Long
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
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.meta.PropId
import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.runtime.ImmutableSpi
import org.babyfish.jimmer.runtime.Internal
import org.babyfish.jimmer.runtime.NonSharedList
import org.babyfish.jimmer.runtime.Visibility
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.OneToMany

@DslScope
@GeneratedBy(type = TreeNode::class)
public interface TreeNodeDraft : TreeNode, Draft {
    public override var id: Long

    public override var name: String

    public override var parentNode: TreeNode?

    public var parentNodeId: Long?

    public override var childNodes: List<TreeNode>

    public fun parentNode(): TreeNodeDraft

    public fun childNodes(): MutableList<TreeNodeDraft>

    public object `$` {
        public const val SLOT_ID: Int = 0

        public const val SLOT_NAME: Int = 1

        public const val SLOT_PARENT_NODE: Int = 2

        public const val SLOT_CHILD_NODES: Int = 3

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                TreeNode::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as TreeNode?)
            }
            .id(SLOT_ID, "id", Long::class.java)
            .key(SLOT_NAME, "name", String::class.java, false)
            .keyReference(SLOT_PARENT_NODE, "parentNode", ManyToOne::class.java,
                    TreeNode::class.java, true)
            .add(SLOT_CHILD_NODES, "childNodes", OneToMany::class.java, TreeNode::class.java, false)
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
                SLOT_ID ->
                	id
                SLOT_NAME ->
                	name
                SLOT_PARENT_NODE ->
                	parentNode
                SLOT_CHILD_NODES ->
                	childNodes
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "id" ->
                	id
                "name" ->
                	name
                "parentNode" ->
                	parentNode
                "childNodes" ->
                	childNodes
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __type(): ImmutableType = `$`.type
        }

        private class Impl : Implementor, Cloneable, Serializable {
            @get:JsonIgnore
            internal var __visibility: Visibility? = null

            internal var __idValue: Long = 0

            internal var __idLoaded: Boolean = false

            internal var __nameValue: String? = null

            internal var __parentNodeValue: TreeNode? = null

            internal var __parentNodeLoaded: Boolean = false

            internal var __childNodesValue: NonSharedList<TreeNode>? = null

            public override val id: Long
                @JsonIgnore
                get() {
                    if (!__idLoaded) {
                        throw UnloadedException(TreeNode::class.java, "id")
                    }
                    return __idValue
                }

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(TreeNode::class.java, "name")
                    }
                    return __nameValue
                }

            public override val parentNode: TreeNode?
                @JsonIgnore
                get() {
                    if (!__parentNodeLoaded) {
                        throw UnloadedException(TreeNode::class.java, "parentNode")
                    }
                    return __parentNodeValue
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
                SLOT_ID ->
                	__idLoaded
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_PARENT_NODE ->
                	__parentNodeLoaded
                SLOT_CHILD_NODES ->
                	__childNodesValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "id" ->
                	__idLoaded
                "name" ->
                	__nameValue !== null
                "parentNode" ->
                	__parentNodeLoaded
                "childNodes" ->
                	__childNodesValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                    prop
                )

            }

            public override fun __isVisible(prop: PropId): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop.asIndex()) {
                    -1 ->
                    	__isVisible(prop.asName())
                    SLOT_ID ->
                    	__visibility.visible(SLOT_ID)
                    SLOT_NAME ->
                    	__visibility.visible(SLOT_NAME)
                    SLOT_PARENT_NODE ->
                    	__visibility.visible(SLOT_PARENT_NODE)
                    SLOT_CHILD_NODES ->
                    	__visibility.visible(SLOT_CHILD_NODES)
                    else -> true
                }
            }

            public override fun __isVisible(prop: String): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop) {
                    "id" ->
                    	__visibility.visible(SLOT_ID)
                    "name" ->
                    	__visibility.visible(SLOT_NAME)
                    "parentNode" ->
                    	__visibility.visible(SLOT_PARENT_NODE)
                    "childNodes" ->
                    	__visibility.visible(SLOT_CHILD_NODES)
                    else -> true
                }
            }

            public fun __shallowHashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__idLoaded) {
                    hash = 31 * hash + __idValue.hashCode()
                }
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__parentNodeLoaded) {
                    hash = 31 * hash + System.identityHashCode(__parentNodeValue)
                }
                if (__childNodesValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__childNodesValue)
                }
                return hash
            }

            public override fun hashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__idLoaded) {
                    hash = 31 * hash + __idValue.hashCode()
                    return hash
                }
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__parentNodeLoaded) {
                    hash = 31 * hash + (__parentNodeValue?.hashCode() ?: 0)
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
                if (__isVisible(PropId.byIndex(SLOT_ID)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false
                }
                val __idLoaded = 
                    this.__idLoaded
                if (__idLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_ID)))) {
                    return false
                }
                if (__idLoaded && this.__idValue != __other.id) {
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
                if (__isVisible(PropId.byIndex(SLOT_PARENT_NODE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PARENT_NODE))) {
                    return false
                }
                val __parentNodeLoaded = 
                    this.__parentNodeLoaded
                if (__parentNodeLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PARENT_NODE)))) {
                    return false
                }
                if (__parentNodeLoaded && this.__parentNodeValue !== __other.parentNode) {
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
                if (__isVisible(PropId.byIndex(SLOT_ID)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false
                }
                val __idLoaded = 
                    this.__idLoaded
                if (__idLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_ID)))) {
                    return false
                }
                if (__idLoaded) {
                    return this.__idValue == __other.id
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
                if (__isVisible(PropId.byIndex(SLOT_PARENT_NODE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PARENT_NODE))) {
                    return false
                }
                val __parentNodeLoaded = 
                    this.__parentNodeLoaded
                if (__parentNodeLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PARENT_NODE)))) {
                    return false
                }
                if (__parentNodeLoaded && this.__parentNodeValue != __other.parentNode) {
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

            public override var id: Long
                @JsonIgnore
                get() = (__modified ?: __base!!).id
                set(id) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__idValue = id
                    __tmpModified.__idLoaded = true
                }

            public override var name: String
                @JsonIgnore
                get() = (__modified ?: __base!!).name
                set(name) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__nameValue = name
                }

            public override var parentNode: TreeNode?
                @JsonIgnore
                get() = __ctx.toDraftObject((__modified ?: __base!!).parentNode)
                set(parentNode) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__parentNodeValue = parentNode
                    __tmpModified.__parentNodeLoaded = true
                }

            public override var parentNodeId: Long?
                get() = parentNode?.id
                set(parentNodeId) {
                    if (parentNodeId === null) {
                        this.parentNode = null
                        return
                    }
                    parentNode().id = parentNodeId
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

            public override fun parentNode(): TreeNodeDraft {
                if (!__isLoaded(PropId.byIndex(SLOT_PARENT_NODE)) || parentNode === null) {
                    parentNode = `$`.produce {}
                }
                return parentNode as TreeNodeDraft
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
                    SLOT_ID ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__idLoaded = false
                    SLOT_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    SLOT_PARENT_NODE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__parentNodeLoaded = false
                    SLOT_CHILD_NODES ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__childNodesValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __unload(prop: String): Unit {
                when (prop) {
                    "id" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__idLoaded = false
                    "name" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    "parentNode" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__parentNodeLoaded = false
                    "childNodes" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__childNodesValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: PropId, `value`: Any?): Unit {
                when (prop.asIndex()) {
                    -1 ->
                    	__set(prop.asName(), value)
                    SLOT_ID ->
                    	this.id = value as Long?
                    	?: throw IllegalArgumentException("'id cannot be null")
                    SLOT_NAME ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    SLOT_PARENT_NODE ->
                    	this.parentNode = value as TreeNode?
                    SLOT_CHILD_NODES ->
                    	this.childNodes = value as List<TreeNode>?
                    	?: throw IllegalArgumentException("'childNodes cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: String, `value`: Any?): Unit {
                when (prop) {
                    "id" ->
                    	this.id = value as Long?
                    	?: throw IllegalArgumentException("'id cannot be null")
                    "name" ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    "parentNode" ->
                    	this.parentNode = value as TreeNode?
                    "childNodes" ->
                    	this.childNodes = value as List<TreeNode>?
                    	?: throw IllegalArgumentException("'childNodes cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.TreeNode\": " + 
                        prop
                    )

                }
            }

            public override fun __show(prop: PropId, visible: Boolean): Unit {
                val __visibility = (__modified ?: __base!!).__visibility
                    ?: if (visible) {
                        null
                    } else {
                        Visibility.of(4).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop.asIndex()) {
                    -1 ->
                    	__show(prop.asName(), visible)
                    SLOT_ID ->
                    	__visibility.show(SLOT_ID, visible)
                    SLOT_NAME ->
                    	__visibility.show(SLOT_NAME, visible)
                    SLOT_PARENT_NODE ->
                    	__visibility.show(SLOT_PARENT_NODE, visible)
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
                        Visibility.of(4).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "id" ->
                    	__visibility.show(SLOT_ID, visible)
                    "name" ->
                    	__visibility.show(SLOT_NAME, visible)
                    "parentNode" ->
                    	__visibility.show(SLOT_PARENT_NODE, visible)
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
                        if (__isLoaded(PropId.byIndex(SLOT_PARENT_NODE))) {
                            val oldValue = base!!.parentNode
                            val newValue = __ctx.resolveObject(oldValue)
                            if (oldValue !== newValue) {
                                parentNode = newValue
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
                        __tmpModified.__parentNodeValue =
                                __ctx.resolveObject(__tmpModified.__parentNodeValue)
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
        private var id: Long? = null

        private var name: String? = null

        private var __parentNodeLoaded: Boolean = false

        private var parentNode: TreeNode? = null

        private var childNodes: List<TreeNode>? = null

        public fun id(id: Long?): MapStruct {
            this.id = id
            return this
        }

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun parentNode(parentNode: TreeNode?): MapStruct {
            this.__parentNodeLoaded = true
            this.parentNode = parentNode
            return this
        }

        public fun childNodes(childNodes: List<TreeNode>?): MapStruct {
            this.childNodes = childNodes ?: emptyList()
            return this
        }

        public fun build(): TreeNode = TreeNodeDraft
        .`$`.produce {val __that = this@MapStruct
            __that.id?.let  {
                id = it
            }
            __that.name?.let  {
                name = it
            }
            if (__that.__parentNodeLoaded) {
                parentNode = __that.parentNode
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
