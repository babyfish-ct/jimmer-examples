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
import org.babyfish.jimmer.meta.ImmutablePropCategory
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.meta.PropId
import org.babyfish.jimmer.runtime.DraftContext
import org.babyfish.jimmer.runtime.DraftSpi
import org.babyfish.jimmer.runtime.ImmutableSpi
import org.babyfish.jimmer.runtime.Internal
import org.babyfish.jimmer.runtime.NonSharedList
import org.babyfish.jimmer.runtime.Visibility
import org.babyfish.jimmer.sql.OneToMany

@DslScope
@GeneratedBy(type = BookStore::class)
public interface BookStoreDraft : BookStore, Draft {
    public override var id: Long

    public override var name: String

    public override var website: String?

    public override var books: List<Book>

    public fun books(): MutableList<BookDraft>

    public object `$` {
        public const val SLOT_ID: Int = 0

        public const val SLOT_NAME: Int = 1

        public const val SLOT_WEBSITE: Int = 2

        public const val SLOT_BOOKS: Int = 3

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                BookStore::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as BookStore?)
            }
            .id(SLOT_ID, "id", Long::class.java)
            .key(SLOT_NAME, "name", String::class.java, false)
            .add(SLOT_WEBSITE, "website", ImmutablePropCategory.SCALAR, String::class.java, true)
            .add(SLOT_BOOKS, "books", OneToMany::class.java, Book::class.java, false)
            .build()

        public fun produce(base: BookStore? = null, block: BookStoreDraft.() -> Unit): BookStore {
            val consumer = DraftConsumer<BookStoreDraft> { block(it) }
            return Internal.produce(type, base, consumer) as BookStore
        }

        private abstract interface Implementor : BookStore, ImmutableSpi {
            public val dummyPropForJacksonError__: Int
                get() = throw ImmutableModuleRequiredException()

            public override fun __get(prop: PropId): Any? = when (prop.asIndex()) {
                -1 ->
                	__get(prop.asName())
                SLOT_ID ->
                	id
                SLOT_NAME ->
                	name
                SLOT_WEBSITE ->
                	website
                SLOT_BOOKS ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "id" ->
                	id
                "name" ->
                	name
                "website" ->
                	website
                "books" ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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

            internal var __websiteValue: String? = null

            internal var __websiteLoaded: Boolean = false

            internal var __booksValue: NonSharedList<Book>? = null

            public override val id: Long
                @JsonIgnore
                get() {
                    if (!__idLoaded) {
                        throw UnloadedException(BookStore::class.java, "id")
                    }
                    return __idValue
                }

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(BookStore::class.java, "name")
                    }
                    return __nameValue
                }

            public override val website: String?
                @JsonIgnore
                get() {
                    if (!__websiteLoaded) {
                        throw UnloadedException(BookStore::class.java, "website")
                    }
                    return __websiteValue
                }

            public override val books: List<Book>
                @JsonIgnore
                get() {
                    val __booksValue = this.__booksValue
                    if (__booksValue === null) {
                        throw UnloadedException(BookStore::class.java, "books")
                    }
                    return __booksValue
                }

            public override fun clone(): Impl = super.clone() as Impl

            public override fun __isLoaded(prop: PropId): Boolean = when (prop.asIndex()) {
                -1 ->
                	__isLoaded(prop.asName())
                SLOT_ID ->
                	__idLoaded
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_WEBSITE ->
                	__websiteLoaded
                SLOT_BOOKS ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "id" ->
                	__idLoaded
                "name" ->
                	__nameValue !== null
                "website" ->
                	__websiteLoaded
                "books" ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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
                    SLOT_WEBSITE ->
                    	__visibility.visible(SLOT_WEBSITE)
                    SLOT_BOOKS ->
                    	__visibility.visible(SLOT_BOOKS)
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
                    "website" ->
                    	__visibility.visible(SLOT_WEBSITE)
                    "books" ->
                    	__visibility.visible(SLOT_BOOKS)
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
                if (__websiteLoaded) {
                    hash = 31 * hash + (__websiteValue?.hashCode() ?: 0)
                }
                if (__booksValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__booksValue)
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
                if (__websiteLoaded) {
                    hash = 31 * hash + (__websiteValue?.hashCode() ?: 0)
                }
                if (__booksValue !== null) {
                    hash = 31 * hash + __booksValue.hashCode()
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
                if (__isVisible(PropId.byIndex(SLOT_WEBSITE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_WEBSITE))) {
                    return false
                }
                val __websiteLoaded = 
                    this.__websiteLoaded
                if (__websiteLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_WEBSITE)))) {
                    return false
                }
                if (__websiteLoaded && this.__websiteValue != __other.website) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_BOOKS)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_BOOKS))) {
                    return false
                }
                val __booksLoaded = 
                    this.__booksValue !== null
                if (__booksLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_BOOKS)))) {
                    return false
                }
                if (__booksLoaded && this.__booksValue !== __other.books) {
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
                if (__isVisible(PropId.byIndex(SLOT_WEBSITE)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_WEBSITE))) {
                    return false
                }
                val __websiteLoaded = 
                    this.__websiteLoaded
                if (__websiteLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_WEBSITE)))) {
                    return false
                }
                if (__websiteLoaded && this.__websiteValue != __other.website) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_BOOKS)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_BOOKS))) {
                    return false
                }
                val __booksLoaded = 
                    this.__booksValue !== null
                if (__booksLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_BOOKS)))) {
                    return false
                }
                if (__booksLoaded && this.__booksValue != __other.books) {
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
            base: BookStore?,
        ) : Implementor, BookStoreDraft, DraftSpi {
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

            public override var website: String?
                @JsonIgnore
                get() = (__modified ?: __base!!).website
                set(website) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__websiteValue = website
                    __tmpModified.__websiteLoaded = true
                }

            public override var books: List<Book>
                @JsonIgnore
                get() = __ctx.toDraftList((__modified ?: __base!!).books, Book::class.java, true)
                set(books) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__booksValue = NonSharedList.of(__tmpModified.__booksValue, books)
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

            @Suppress("UNCHECKED_CAST")
            public override fun books(): MutableList<BookDraft> {
                if (!__isLoaded(PropId.byIndex(SLOT_BOOKS))) {
                    books = emptyList()
                }
                return books as MutableList<BookDraft>
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
                    SLOT_WEBSITE ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__websiteLoaded = false
                    SLOT_BOOKS ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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
                    "website" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__websiteLoaded = false
                    "books" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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
                    SLOT_WEBSITE ->
                    	this.website = value as String?
                    SLOT_BOOKS ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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
                    "website" ->
                    	this.website = value as String?
                    "books" ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.BookStore\": " + 
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
                    SLOT_WEBSITE ->
                    	__visibility.show(SLOT_WEBSITE, visible)
                    SLOT_BOOKS ->
                    	__visibility.show(SLOT_BOOKS, visible)
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
                    "website" ->
                    	__visibility.show(SLOT_WEBSITE, visible)
                    "books" ->
                    	__visibility.show(SLOT_BOOKS, visible)
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
                        if (__isLoaded(PropId.byIndex(SLOT_BOOKS))) {
                            val oldValue = base!!.books
                            val newValue = __ctx.resolveList(oldValue)
                            if (oldValue !== newValue) {
                                books = newValue
                            }
                        }
                        __tmpModified = __modified
                    } else {
                        __tmpModified.__booksValue = NonSharedList.of(__tmpModified.__booksValue,
                                __ctx.resolveList(__tmpModified.__booksValue))
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

        private var __websiteLoaded: Boolean = false

        private var website: String? = null

        private var books: List<Book>? = null

        public fun id(id: Long?): MapStruct {
            this.id = id
            return this
        }

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun website(website: String?): MapStruct {
            this.__websiteLoaded = true
            this.website = website
            return this
        }

        public fun books(books: List<Book>?): MapStruct {
            this.books = books ?: emptyList()
            return this
        }

        public fun build(): BookStore = BookStoreDraft
        .`$`.produce {val __that = this@MapStruct
            __that.id?.let  {
                id = it
            }
            __that.name?.let  {
                name = it
            }
            if (__that.__websiteLoaded) {
                website = __that.website
            }
            __that.books?.let  {
                books = it
            }
        }
    }
}

public fun ImmutableCreator<BookStore>.`by`(base: BookStore? = null,
        block: BookStoreDraft.() -> Unit): BookStore = BookStoreDraft.`$`.produce(base, block)

public fun MutableList<BookStoreDraft>.addBy(base: BookStore? = null,
        block: BookStoreDraft.() -> Unit): MutableList<BookStoreDraft> {
    add(BookStoreDraft.`$`.produce(base, block) as BookStoreDraft)
    return this
}

public fun BookStore.copy(block: BookStoreDraft.() -> Unit): BookStore =
        BookStoreDraft.`$`.produce(this, block)
