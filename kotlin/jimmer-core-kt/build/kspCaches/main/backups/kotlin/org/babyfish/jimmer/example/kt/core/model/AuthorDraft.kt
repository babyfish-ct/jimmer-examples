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
@GeneratedBy(type = Author::class)
public interface AuthorDraft : Author, Draft {
    public override var name: String

    public override var lastModifiedTime: LocalDateTime

    public override var books: List<Book>

    public fun books(): MutableList<BookDraft>

    public object `$` {
        public const val SLOT_NAME: Int = 0

        public const val SLOT_LAST_MODIFIED_TIME: Int = 1

        public const val SLOT_BOOKS: Int = 2

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                Author::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as Author?)
            }
            .add(SLOT_NAME, "name", ImmutablePropCategory.SCALAR, String::class.java, false)
            .add(SLOT_LAST_MODIFIED_TIME, "lastModifiedTime", ImmutablePropCategory.SCALAR,
                    LocalDateTime::class.java, false)
            .add(SLOT_BOOKS, "books", ImmutablePropCategory.REFERENCE_LIST, Book::class.java, false)
            .build()

        public fun produce(base: Author? = null, block: AuthorDraft.() -> Unit): Author {
            val consumer = DraftConsumer<AuthorDraft> { block(it) }
            return Internal.produce(type, base, consumer) as Author
        }

        private abstract interface Implementor : Author, ImmutableSpi {
            public val dummyPropForJacksonError__: Int
                get() = throw ImmutableModuleRequiredException()

            public override fun __get(prop: PropId): Any? = when (prop.asIndex()) {
                -1 ->
                	__get(prop.asName())
                SLOT_NAME ->
                	name
                SLOT_LAST_MODIFIED_TIME ->
                	lastModifiedTime
                SLOT_BOOKS ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "name" ->
                	name
                "lastModifiedTime" ->
                	lastModifiedTime
                "books" ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
                    prop
                )

            }

            public override fun __type(): ImmutableType = `$`.type
        }

        private class Impl : Implementor, Cloneable, Serializable {
            @get:JsonIgnore
            internal var __visibility: Visibility? = null

            internal var __nameValue: String? = null

            internal var __lastModifiedTimeValue: LocalDateTime? = null

            internal var __booksValue: NonSharedList<Book>? = null

            public override val name: String
                @JsonIgnore
                get() {
                    val __nameValue = this.__nameValue
                    if (__nameValue === null) {
                        throw UnloadedException(Author::class.java, "name")
                    }
                    return __nameValue
                }

            public override val lastModifiedTime: LocalDateTime
                @JsonIgnore
                get() {
                    val __lastModifiedTimeValue = this.__lastModifiedTimeValue
                    if (__lastModifiedTimeValue === null) {
                        throw UnloadedException(Author::class.java, "lastModifiedTime")
                    }
                    return __lastModifiedTimeValue
                }

            public override val books: List<Book>
                @JsonIgnore
                get() {
                    val __booksValue = this.__booksValue
                    if (__booksValue === null) {
                        throw UnloadedException(Author::class.java, "books")
                    }
                    return __booksValue
                }

            public override fun clone(): Impl = super.clone() as Impl

            public override fun __isLoaded(prop: PropId): Boolean = when (prop.asIndex()) {
                -1 ->
                	__isLoaded(prop.asName())
                SLOT_NAME ->
                	__nameValue !== null
                SLOT_LAST_MODIFIED_TIME ->
                	__lastModifiedTimeValue !== null
                SLOT_BOOKS ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "name" ->
                	__nameValue !== null
                "lastModifiedTime" ->
                	__lastModifiedTimeValue !== null
                "books" ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
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
                    SLOT_LAST_MODIFIED_TIME ->
                    	__visibility.visible(SLOT_LAST_MODIFIED_TIME)
                    SLOT_BOOKS ->
                    	__visibility.visible(SLOT_BOOKS)
                    else -> true
                }
            }

            public override fun __isVisible(prop: String): Boolean {
                val __visibility = this.__visibility ?: return true
                return when (prop) {
                    "name" ->
                    	__visibility.visible(SLOT_NAME)
                    "lastModifiedTime" ->
                    	__visibility.visible(SLOT_LAST_MODIFIED_TIME)
                    "books" ->
                    	__visibility.visible(SLOT_BOOKS)
                    else -> true
                }
            }

            public fun __shallowHashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__lastModifiedTimeValue !== null) {
                    hash = 31 * hash + __lastModifiedTimeValue.hashCode()
                }
                if (__booksValue !== null) {
                    hash = 31 * hash + System.identityHashCode(__booksValue)
                }
                return hash
            }

            public override fun hashCode(): Int {
                var hash = __visibility?.hashCode() ?: 0
                if (__nameValue !== null) {
                    hash = 31 * hash + __nameValue.hashCode()
                }
                if (__lastModifiedTimeValue !== null) {
                    hash = 31 * hash + __lastModifiedTimeValue.hashCode()
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
            base: Author?,
        ) : Implementor, AuthorDraft, DraftSpi {
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

            public override var lastModifiedTime: LocalDateTime
                @JsonIgnore
                get() = (__modified ?: __base!!).lastModifiedTime
                set(lastModifiedTime) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__lastModifiedTimeValue = lastModifiedTime
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
                    SLOT_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__nameValue = null
                    SLOT_LAST_MODIFIED_TIME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastModifiedTimeValue = null
                    SLOT_BOOKS ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
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
                    "lastModifiedTime" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastModifiedTimeValue = null
                    "books" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
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
                    SLOT_LAST_MODIFIED_TIME ->
                    	this.lastModifiedTime = value as LocalDateTime?
                    	?: throw IllegalArgumentException("'lastModifiedTime cannot be null")
                    SLOT_BOOKS ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: String, `value`: Any?): Unit {
                when (prop) {
                    "name" ->
                    	this.name = value as String?
                    	?: throw IllegalArgumentException("'name cannot be null")
                    "lastModifiedTime" ->
                    	this.lastModifiedTime = value as LocalDateTime?
                    	?: throw IllegalArgumentException("'lastModifiedTime cannot be null")
                    "books" ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.kt.core.model.Author\": " + 
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
                    SLOT_LAST_MODIFIED_TIME ->
                    	__visibility.show(SLOT_LAST_MODIFIED_TIME, visible)
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
                        Visibility.of(3).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "name" ->
                    	__visibility.show(SLOT_NAME, visible)
                    "lastModifiedTime" ->
                    	__visibility.show(SLOT_LAST_MODIFIED_TIME, visible)
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
        private var name: String? = null

        private var lastModifiedTime: LocalDateTime? = null

        private var books: List<Book>? = null

        public fun name(name: String?): MapStruct {
            this.name = name
            return this
        }

        public fun lastModifiedTime(lastModifiedTime: LocalDateTime?): MapStruct {
            this.lastModifiedTime = lastModifiedTime
            return this
        }

        public fun books(books: List<Book>?): MapStruct {
            this.books = books ?: emptyList()
            return this
        }

        public fun build(): Author = AuthorDraft
        .`$`.produce {val __that = this@MapStruct
            __that.name?.let  {
                name = it
            }
            __that.lastModifiedTime?.let  {
                lastModifiedTime = it
            }
            __that.books?.let  {
                books = it
            }
        }
    }
}

public fun ImmutableCreator<Author>.`by`(base: Author? = null, block: AuthorDraft.() -> Unit):
        Author = AuthorDraft.`$`.produce(base, block)

public fun MutableList<AuthorDraft>.addBy(base: Author? = null, block: AuthorDraft.() -> Unit):
        MutableList<AuthorDraft> {
    add(AuthorDraft.`$`.produce(base, block) as AuthorDraft)
    return this
}

public fun Author.copy(block: AuthorDraft.() -> Unit): Author = AuthorDraft.`$`.produce(this, block)
