@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)

package org.babyfish.jimmer.example.kt.core.model

import com.fasterxml.jackson.`annotation`.JsonIgnore
import java.io.Serializable
import java.lang.System
import java.time.LocalDateTime
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
@GeneratedBy(type = Book::class)
public interface BookDraft : Book, Draft {
    public override var name: String

    public override var store: BookStore?

    public override var price: Int

    public override var lastModifiedTime: LocalDateTime

    public override var authors: List<Author>

    public fun store(): BookStoreDraft

    public fun authors(): MutableList<AuthorDraft>

    public object `$` {
        public const val SLOT_NAME: Int = 0

        public const val SLOT_STORE: Int = 1

        public const val SLOT_PRICE: Int = 2

        public const val SLOT_LAST_MODIFIED_TIME: Int = 3

        public const val SLOT_AUTHORS: Int = 4

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                Book::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as Book?)
            }
            .add(SLOT_NAME, "name", ImmutablePropCategory.SCALAR, String::class.java, false)
            .add(SLOT_STORE, "store", ImmutablePropCategory.REFERENCE, BookStore::class.java, true)
            .add(SLOT_PRICE, "price", ImmutablePropCategory.SCALAR, Int::class.java, false)
            .add(SLOT_LAST_MODIFIED_TIME, "lastModifiedTime", ImmutablePropCategory.SCALAR,
                    LocalDateTime::class.java, false)
            .add(SLOT_AUTHORS, "authors", ImmutablePropCategory.REFERENCE_LIST, Author::class.java,
                    false)
            .build()

        public fun produce(base: Book? = null, block: BookDraft.() -> Unit): Book {
            val consumer = DraftConsumer<BookDraft> { block(it) }
            return Internal.produce(type, base, consumer) as Book
        }

        private abstract interface Implementor : Book, ImmutableSpi {
            public val dummyPropForJacksonError__: Int
                get() = throw ImmutableModuleRequiredException()

            public override fun __get(prop: PropId): Any? = when (prop.asIndex()) {
                -1 ->
                	__get(prop.asName())
                SLOT_NAME ->
                	name
                SLOT_STORE ->
                	store
                SLOT_PRICE ->
                	price
                SLOT_LAST_MODIFIED_TIME ->
                	lastModifiedTime
                SLOT_AUTHORS ->
                	authors
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "name" ->
                	name
                "store" ->
                	store
                "price" ->
                	price
                "lastModifiedTime" ->
                	lastModifiedTime
                "authors" ->
                	authors
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
                    prop
                )

            }

            public override fun __type(): ImmutableType = `$`.type
        }

        private class Impl : Implementor, Cloneable, Serializable {
            @get:JsonIgnore
            internal var __visibility: Visibility? = null

            internal var __nameValue: String? = null

            internal var __storeValue: BookStore? = null

            internal var __storeLoaded: Boolean = false

            internal var __priceValue: Int = 0

            internal var __priceLoaded: Boolean = false

            internal var __lastModifiedTimeValue: LocalDateTime? = null

            internal var __authorsValue: NonSharedList<Author>? = null

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(Book::class.java, "name")
                    }
                    return __nameValue
                }

            public override val store: BookStore?
                @JsonIgnore
                get() {
                    if (!__storeLoaded) {
                        throw UnloadedException(Book::class.java, "store")
                    }
                    return __storeValue
                }

            public override val price: Int
                @JsonIgnore
                get() {
                    if (!__priceLoaded) {
                        throw UnloadedException(Book::class.java, "price")
                    }
                    return __priceValue
                }

            public override val lastModifiedTime: LocalDateTime
                @JsonIgnore
                get() {
                    val __lastModifiedTimeValue = this.__lastModifiedTimeValue
                    if (__lastModifiedTimeValue === null) {
                        throw UnloadedException(Book::class.java, "lastModifiedTime")
                    }
                    return __lastModifiedTimeValue
                }

            public override val authors: List<Author>
                @JsonIgnore
                get() {
                    val __authorsValue = this.__authorsValue
                    if (__authorsValue === null) {
                        throw UnloadedException(Book::class.java, "authors")
                    }
                    return __authorsValue
                }

            public override fun clone(): Impl = super.clone() as Impl

            public override fun __isLoaded(prop: PropId): Boolean = when (prop.asIndex()) {
                -1 ->
                	__isLoaded(prop.asName())
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_STORE ->
                	__storeLoaded
                SLOT_PRICE ->
                	__priceLoaded
                SLOT_LAST_MODIFIED_TIME ->
                	__lastModifiedTimeValue !== null
                SLOT_AUTHORS ->
                	__authorsValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "name" ->
                	__nameValue !== null
                "store" ->
                	__storeLoaded
                "price" ->
                	__priceLoaded
                "lastModifiedTime" ->
                	__lastModifiedTimeValue !== null
                "authors" ->
                	__authorsValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
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
                    SLOT_STORE ->
                    	__visibility.visible(SLOT_STORE)
                    SLOT_PRICE ->
                    	__visibility.visible(SLOT_PRICE)
                    SLOT_LAST_MODIFIED_TIME ->
                    	__visibility.visible(SLOT_LAST_MODIFIED_TIME)
                    SLOT_AUTHORS ->
                    	__visibility.visible(SLOT_AUTHORS)
                    else -> true
                }
            }

            public override fun __isVisible(prop: String): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop) {
                    "name" ->
                    	__visibility.visible(SLOT_NAME)
                    "store" ->
                    	__visibility.visible(SLOT_STORE)
                    "price" ->
                    	__visibility.visible(SLOT_PRICE)
                    "lastModifiedTime" ->
                    	__visibility.visible(SLOT_LAST_MODIFIED_TIME)
                    "authors" ->
                    	__visibility.visible(SLOT_AUTHORS)
                    else -> true
                }
            }

            public fun __shallowHashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__storeLoaded) {
                    hash = 31 * hash + System.identityHashCode(__storeValue)
                }
                if (__priceLoaded) {
                    hash = 31 * hash + __priceValue.hashCode()
                }
                if (__lastModifiedTimeValue !== null) {
                    hash = 31 * hash + __lastModifiedTimeValue.hashCode()
                }
                if (__authorsValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__authorsValue)
                }
                return hash
            }

            public override fun hashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__storeLoaded) {
                    hash = 31 * hash + (__storeValue?.hashCode() ?: 0)
                }
                if (__priceLoaded) {
                    hash = 31 * hash + __priceValue.hashCode()
                }
                if (__lastModifiedTimeValue !== null) {
                    hash = 31 * hash + __lastModifiedTimeValue.hashCode()
                }
                if (__authorsValue !== null) {
                    hash = 31 * hash + __authorsValue.hashCode()
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
                if (__isVisible(PropId.byIndex(SLOT_STORE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_STORE))) {
                    return false
                }
                val __storeLoaded = 
                    this.__storeLoaded
                if (__storeLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_STORE)))) {
                    return false
                }
                if (__storeLoaded && this.__storeValue !== __other.store) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PRICE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PRICE))) {
                    return false
                }
                val __priceLoaded = 
                    this.__priceLoaded
                if (__priceLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PRICE)))) {
                    return false
                }
                if (__priceLoaded && this.__priceValue != __other.price) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_MODIFIED_TIME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_LAST_MODIFIED_TIME))) {
                    return false
                }
                val __lastModifiedTimeLoaded = 
                    this.__lastModifiedTimeValue !== null
                if (__lastModifiedTimeLoaded !=
                        (__other.__isLoaded(PropId.byIndex(SLOT_LAST_MODIFIED_TIME)))) {
                    return false
                }
                if (__lastModifiedTimeLoaded && this.__lastModifiedTimeValue !=
                        __other.lastModifiedTime) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_AUTHORS)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_AUTHORS))) {
                    return false
                }
                val __authorsLoaded = 
                    this.__authorsValue !== null
                if (__authorsLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_AUTHORS)))) {
                    return false
                }
                if (__authorsLoaded && this.__authorsValue !== __other.authors) {
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
                if (__isVisible(PropId.byIndex(SLOT_STORE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_STORE))) {
                    return false
                }
                val __storeLoaded = 
                    this.__storeLoaded
                if (__storeLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_STORE)))) {
                    return false
                }
                if (__storeLoaded && this.__storeValue != __other.store) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PRICE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PRICE))) {
                    return false
                }
                val __priceLoaded = 
                    this.__priceLoaded
                if (__priceLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PRICE)))) {
                    return false
                }
                if (__priceLoaded && this.__priceValue != __other.price) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_MODIFIED_TIME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_LAST_MODIFIED_TIME))) {
                    return false
                }
                val __lastModifiedTimeLoaded = 
                    this.__lastModifiedTimeValue !== null
                if (__lastModifiedTimeLoaded !=
                        (__other.__isLoaded(PropId.byIndex(SLOT_LAST_MODIFIED_TIME)))) {
                    return false
                }
                if (__lastModifiedTimeLoaded && this.__lastModifiedTimeValue !=
                        __other.lastModifiedTime) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_AUTHORS)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_AUTHORS))) {
                    return false
                }
                val __authorsLoaded = 
                    this.__authorsValue !== null
                if (__authorsLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_AUTHORS)))) {
                    return false
                }
                if (__authorsLoaded && this.__authorsValue != __other.authors) {
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
            base: Book?,
        ) : Implementor, BookDraft, DraftSpi {
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

            public override var store: BookStore?
                @JsonIgnore
                get() = __ctx.toDraftObject((__modified ?: __base!!).store)
                set(store) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__storeValue = store
                    __tmpModified.__storeLoaded = true
                }

            public override var price: Int
                @JsonIgnore
                get() = (__modified ?: __base!!).price
                set(price) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__priceValue = price
                    __tmpModified.__priceLoaded = true
                }

            public override var lastModifiedTime: LocalDateTime
                @JsonIgnore
                get() = (__modified ?: __base!!).lastModifiedTime
                set(lastModifiedTime) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__lastModifiedTimeValue = lastModifiedTime
                }

            public override var authors: List<Author>
                @JsonIgnore
                get() = __ctx.toDraftList((__modified ?: __base!!).authors, Author::class.java,
                        true)
                set(authors) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__authorsValue = NonSharedList.of(__tmpModified.__authorsValue,
                            authors)
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

            public override fun store(): BookStoreDraft {
                if (!__isLoaded(PropId.byIndex(SLOT_STORE)) || store === null) {
                    store = BookStoreDraft.`$`.produce {}
                }
                return store as BookStoreDraft
            }

            @Suppress("UNCHECKED_CAST")
            public override fun authors(): MutableList<AuthorDraft> {
                if (!__isLoaded(PropId.byIndex(SLOT_AUTHORS))) {
                    authors = emptyList()
                }
                return authors as MutableList<AuthorDraft>
            }

            public override fun __unload(prop: PropId): Unit {
                when (prop.asIndex()) {
                    -1 ->
                    	__unload(prop.asName())
                    SLOT_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    SLOT_STORE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__storeLoaded = false
                    SLOT_PRICE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__priceLoaded = false
                    SLOT_LAST_MODIFIED_TIME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastModifiedTimeValue = null
                    SLOT_AUTHORS ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__authorsValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
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
                    "store" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__storeLoaded = false
                    "price" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__priceLoaded = false
                    "lastModifiedTime" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastModifiedTimeValue = null
                    "authors" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__authorsValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
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
                    SLOT_STORE ->
                    	this.store = value as BookStore?
                    SLOT_PRICE ->
                    	this.price = value as Int?
                    	?: throw IllegalArgumentException("'price cannot be null")
                    SLOT_LAST_MODIFIED_TIME ->
                    	this.lastModifiedTime = value as LocalDateTime?
                    	?: throw IllegalArgumentException("'lastModifiedTime cannot be null")
                    SLOT_AUTHORS ->
                    	this.authors = value as List<Author>?
                    	?: throw IllegalArgumentException("'authors cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: String, `value`: Any?): Unit {
                when (prop) {
                    "name" ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    "store" ->
                    	this.store = value as BookStore?
                    "price" ->
                    	this.price = value as Int?
                    	?: throw IllegalArgumentException("'price cannot be null")
                    "lastModifiedTime" ->
                    	this.lastModifiedTime = value as LocalDateTime?
                    	?: throw IllegalArgumentException("'lastModifiedTime cannot be null")
                    "authors" ->
                    	this.authors = value as List<Author>?
                    	?: throw IllegalArgumentException("'authors cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Book\": " + 
                        prop
                    )

                }
            }

            public override fun __show(prop: PropId, visible: Boolean): Unit {
                val __visibility = (__modified ?: __base!!).__visibility
                    ?: if (visible) {
                        null
                    } else {
                        Visibility.of(5).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop.asIndex()) {
                    -1 ->
                    	__show(prop.asName(), visible)
                    SLOT_NAME ->
                    	__visibility.show(SLOT_NAME, visible)
                    SLOT_STORE ->
                    	__visibility.show(SLOT_STORE, visible)
                    SLOT_PRICE ->
                    	__visibility.show(SLOT_PRICE, visible)
                    SLOT_LAST_MODIFIED_TIME ->
                    	__visibility.show(SLOT_LAST_MODIFIED_TIME, visible)
                    SLOT_AUTHORS ->
                    	__visibility.show(SLOT_AUTHORS, visible)
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
                        Visibility.of(5).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "name" ->
                    	__visibility.show(SLOT_NAME, visible)
                    "store" ->
                    	__visibility.show(SLOT_STORE, visible)
                    "price" ->
                    	__visibility.show(SLOT_PRICE, visible)
                    "lastModifiedTime" ->
                    	__visibility.show(SLOT_LAST_MODIFIED_TIME, visible)
                    "authors" ->
                    	__visibility.show(SLOT_AUTHORS, visible)
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
                        if (__isLoaded(PropId.byIndex(SLOT_STORE))) {
                            val oldValue = base!!.store
                            val newValue = __ctx.resolveObject(oldValue)
                            if (oldValue !== newValue) {
                                store = newValue
                            }
                        }
                        if (__isLoaded(PropId.byIndex(SLOT_AUTHORS))) {
                            val oldValue = base!!.authors
                            val newValue = __ctx.resolveList(oldValue)
                            if (oldValue !== newValue) {
                                authors = newValue
                            }
                        }
                        __tmpModified = __modified
                    } else {
                        __tmpModified.__storeValue = __ctx.resolveObject(__tmpModified.__storeValue)
                        __tmpModified.__authorsValue =
                                NonSharedList.of(__tmpModified.__authorsValue,
                                __ctx.resolveList(__tmpModified.__authorsValue))
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

        private var __storeLoaded: Boolean = false

        private var store: BookStore? = null

        private var price: Int? = null

        private var lastModifiedTime: LocalDateTime? = null

        private var authors: List<Author>? = null

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun store(store: BookStore?): MapStruct {
            this.__storeLoaded = true
            this.store = store
            return this
        }

        public fun price(price: Int?): MapStruct {
            this.price = price
            return this
        }

        public fun lastModifiedTime(lastModifiedTime: LocalDateTime?): MapStruct {
            this.lastModifiedTime = lastModifiedTime
            return this
        }

        public fun authors(authors: List<Author>?): MapStruct {
            this.authors = authors ?: emptyList()
            return this
        }

        public fun build(): Book = BookDraft
        .`$`.produce {val __that = this@MapStruct
            __that.name?.let  {
                name = it
            }
            if (__that.__storeLoaded) {
                store = __that.store
            }
            __that.price?.let  {
                price = it
            }
            __that.lastModifiedTime?.let  {
                lastModifiedTime = it
            }
            __that.authors?.let  {
                authors = it
            }
        }
    }
}

public fun ImmutableCreator<Book>.`by`(base: Book? = null, block: BookDraft.() -> Unit): Book =
        BookDraft.`$`.produce(base, block)

public fun MutableList<BookDraft>.addBy(base: Book? = null, block: BookDraft.() -> Unit):
        MutableList<BookDraft> {
    add(BookDraft.`$`.produce(base, block) as BookDraft)
    return this
}

public fun Book.copy(block: BookDraft.() -> Unit): Book = BookDraft.`$`.produce(this, block)
