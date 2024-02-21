@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)

package org.babyfish.jimmer.example.save.model

import com.fasterxml.jackson.`annotation`.JsonIgnore
import java.io.Serializable
import java.lang.System
import java.math.BigDecimal
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
import org.babyfish.jimmer.meta.ImmutablePropCategory
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.meta.PropId
import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.runtime.ImmutableSpi
import org.babyfish.jimmer.runtime.Internal
import org.babyfish.jimmer.runtime.NonSharedList
import org.babyfish.jimmer.runtime.Visibility
import org.babyfish.jimmer.sql.ManyToMany
import org.babyfish.jimmer.sql.ManyToOne

@DslScope
@GeneratedBy(type = Book::class)
public interface BookDraft : Book, Draft {
    public override var id: Long

    public override var name: String

    public override var edition: Int

    public override var price: BigDecimal

    public override var store: BookStore?

    public var storeId: Long?

    public override var authors: List<Author>

    public fun store(): BookStoreDraft

    public fun authors(): MutableList<AuthorDraft>

    public object `$` {
        public const val SLOT_ID: Int = 0

        public const val SLOT_NAME: Int = 1

        public const val SLOT_EDITION: Int = 2

        public const val SLOT_PRICE: Int = 3

        public const val SLOT_STORE: Int = 4

        public const val SLOT_AUTHORS: Int = 5

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                Book::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as Book?)
            }
            .id(SLOT_ID, "id", Long::class.java)
            .key(SLOT_NAME, "name", String::class.java, false)
            .key(SLOT_EDITION, "edition", Int::class.java, false)
            .add(SLOT_PRICE, "price", ImmutablePropCategory.SCALAR, BigDecimal::class.java, false)
            .add(SLOT_STORE, "store", ManyToOne::class.java, BookStore::class.java, true)
            .add(SLOT_AUTHORS, "authors", ManyToMany::class.java, Author::class.java, false)
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
                SLOT_ID ->
                	id
                SLOT_NAME ->
                	name
                SLOT_EDITION ->
                	edition
                SLOT_PRICE ->
                	price
                SLOT_STORE ->
                	store
                SLOT_AUTHORS ->
                	authors
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "id" ->
                	id
                "name" ->
                	name
                "edition" ->
                	edition
                "price" ->
                	price
                "store" ->
                	store
                "authors" ->
                	authors
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
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

            internal var __editionValue: Int = 0

            internal var __editionLoaded: Boolean = false

            internal var __priceValue: BigDecimal? = null

            internal var __storeValue: BookStore? = null

            internal var __storeLoaded: Boolean = false

            internal var __authorsValue: NonSharedList<Author>? = null

            public override val id: Long
                @JsonIgnore
                get() {
                    if (!__idLoaded) {
                        throw UnloadedException(Book::class.java, "id")
                    }
                    return __idValue
                }

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(Book::class.java, "name")
                    }
                    return __nameValue
                }

            public override val edition: Int
                @JsonIgnore
                get() {
                    if (!__editionLoaded) {
                        throw UnloadedException(Book::class.java, "edition")
                    }
                    return __editionValue
                }

            public override val price: BigDecimal
                @JsonIgnore
                get() {
                    val __priceValue = this.__priceValue
                    if (__priceValue === null) {
                        throw UnloadedException(Book::class.java, "price")
                    }
                    return __priceValue
                }

            public override val store: BookStore?
                @JsonIgnore
                get() {
                    if (!__storeLoaded) {
                        throw UnloadedException(Book::class.java, "store")
                    }
                    return __storeValue
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
                SLOT_ID ->
                	__idLoaded
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_EDITION ->
                	__editionLoaded
                SLOT_PRICE ->
                	__priceValue !== null
                SLOT_STORE ->
                	__storeLoaded
                SLOT_AUTHORS ->
                	__authorsValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "id" ->
                	__idLoaded
                "name" ->
                	__nameValue !== null
                "edition" ->
                	__editionLoaded
                "price" ->
                	__priceValue !== null
                "store" ->
                	__storeLoaded
                "authors" ->
                	__authorsValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
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
                    SLOT_EDITION ->
                    	__visibility.visible(SLOT_EDITION)
                    SLOT_PRICE ->
                    	__visibility.visible(SLOT_PRICE)
                    SLOT_STORE ->
                    	__visibility.visible(SLOT_STORE)
                    SLOT_AUTHORS ->
                    	__visibility.visible(SLOT_AUTHORS)
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
                    "edition" ->
                    	__visibility.visible(SLOT_EDITION)
                    "price" ->
                    	__visibility.visible(SLOT_PRICE)
                    "store" ->
                    	__visibility.visible(SLOT_STORE)
                    "authors" ->
                    	__visibility.visible(SLOT_AUTHORS)
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
                if (__editionLoaded) {
                    hash = 31 * hash + __editionValue.hashCode()
                }
                if (__priceValue !== null) {
                    hash = 31 * hash + __priceValue.hashCode()
                }
                if (__storeLoaded) {
                    hash = 31 * hash + System.identityHashCode(__storeValue)
                }
                if (__authorsValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__authorsValue)
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
                if (__editionLoaded) {
                    hash = 31 * hash + __editionValue.hashCode()
                }
                if (__priceValue !== null) {
                    hash = 31 * hash + __priceValue.hashCode()
                }
                if (__storeLoaded) {
                    hash = 31 * hash + (__storeValue?.hashCode() ?: 0)
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
                if (__isVisible(PropId.byIndex(SLOT_EDITION)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_EDITION))) {
                    return false
                }
                val __editionLoaded = 
                    this.__editionLoaded
                if (__editionLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_EDITION)))) {
                    return false
                }
                if (__editionLoaded && this.__editionValue != __other.edition) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PRICE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PRICE))) {
                    return false
                }
                val __priceLoaded = 
                    this.__priceValue !== null
                if (__priceLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PRICE)))) {
                    return false
                }
                if (__priceLoaded && this.__priceValue != __other.price) {
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
                if (__isVisible(PropId.byIndex(SLOT_EDITION)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_EDITION))) {
                    return false
                }
                val __editionLoaded = 
                    this.__editionLoaded
                if (__editionLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_EDITION)))) {
                    return false
                }
                if (__editionLoaded && this.__editionValue != __other.edition) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_PRICE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_PRICE))) {
                    return false
                }
                val __priceLoaded = 
                    this.__priceValue !== null
                if (__priceLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_PRICE)))) {
                    return false
                }
                if (__priceLoaded && this.__priceValue != __other.price) {
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

            public override var edition: Int
                @JsonIgnore
                get() = (__modified ?: __base!!).edition
                set(edition) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__editionValue = edition
                    __tmpModified.__editionLoaded = true
                }

            public override var price: BigDecimal
                @JsonIgnore
                get() = (__modified ?: __base!!).price
                set(price) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__priceValue = price
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

            public override var storeId: Long?
                get() = store?.id
                set(storeId) {
                    if (storeId === null) {
                        this.store = null
                        return
                    }
                    store().id = storeId
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
                    SLOT_ID ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__idLoaded = false
                    SLOT_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    SLOT_EDITION ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__editionLoaded = false
                    SLOT_PRICE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__priceValue = null
                    SLOT_STORE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__storeLoaded = false
                    SLOT_AUTHORS ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__authorsValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
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
                    "edition" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__editionLoaded = false
                    "price" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__priceValue = null
                    "store" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__storeLoaded = false
                    "authors" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__authorsValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
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
                    SLOT_EDITION ->
                    	this.edition = value as Int?
                    	?: throw IllegalArgumentException("'edition cannot be null")
                    SLOT_PRICE ->
                    	this.price = value as BigDecimal?
                    	?: throw IllegalArgumentException("'price cannot be null")
                    SLOT_STORE ->
                    	this.store = value as BookStore?
                    SLOT_AUTHORS ->
                    	this.authors = value as List<Author>?
                    	?: throw IllegalArgumentException("'authors cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
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
                    "edition" ->
                    	this.edition = value as Int?
                    	?: throw IllegalArgumentException("'edition cannot be null")
                    "price" ->
                    	this.price = value as BigDecimal?
                    	?: throw IllegalArgumentException("'price cannot be null")
                    "store" ->
                    	this.store = value as BookStore?
                    "authors" ->
                    	this.authors = value as List<Author>?
                    	?: throw IllegalArgumentException("'authors cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Book\": " + 
                        prop
                    )

                }
            }

            public override fun __show(prop: PropId, visible: Boolean): Unit {
                val __visibility = (__modified ?: __base!!).__visibility
                    ?: if (visible) {
                        null
                    } else {
                        Visibility.of(6).also{
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
                    SLOT_EDITION ->
                    	__visibility.show(SLOT_EDITION, visible)
                    SLOT_PRICE ->
                    	__visibility.show(SLOT_PRICE, visible)
                    SLOT_STORE ->
                    	__visibility.show(SLOT_STORE, visible)
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
                        Visibility.of(6).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "id" ->
                    	__visibility.show(SLOT_ID, visible)
                    "name" ->
                    	__visibility.show(SLOT_NAME, visible)
                    "edition" ->
                    	__visibility.show(SLOT_EDITION, visible)
                    "price" ->
                    	__visibility.show(SLOT_PRICE, visible)
                    "store" ->
                    	__visibility.show(SLOT_STORE, visible)
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
        private var id: Long? = null

        private var name: String? = null

        private var edition: Int? = null

        private var price: BigDecimal? = null

        private var __storeLoaded: Boolean = false

        private var store: BookStore? = null

        private var authors: List<Author>? = null

        public fun id(id: Long?): MapStruct {
            this.id = id
            return this
        }

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun edition(edition: Int?): MapStruct {
            this.edition = edition
            return this
        }

        public fun price(price: BigDecimal?): MapStruct {
            this.price = price
            return this
        }

        public fun store(store: BookStore?): MapStruct {
            this.__storeLoaded = true
            this.store = store
            return this
        }

        public fun authors(authors: List<Author>?): MapStruct {
            this.authors = authors ?: emptyList()
            return this
        }

        public fun build(): Book = BookDraft
        .`$`.produce {val __that = this@MapStruct
            __that.id?.let  {
                id = it
            }
            __that.name?.let  {
                name = it
            }
            __that.edition?.let  {
                edition = it
            }
            __that.price?.let  {
                price = it
            }
            if (__that.__storeLoaded) {
                store = __that.store
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
