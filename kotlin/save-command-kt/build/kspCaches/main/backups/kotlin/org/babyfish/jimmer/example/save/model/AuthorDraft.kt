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
import org.babyfish.jimmer.sql.ManyToMany

@DslScope
@GeneratedBy(type = Author::class)
public interface AuthorDraft : Author, Draft {
    public override var id: Long

    public override var firstName: String

    public override var lastName: String

    public override var gender: Gender

    public override var books: List<Book>

    public fun books(): MutableList<BookDraft>

    public object `$` {
        public const val SLOT_ID: Int = 0

        public const val SLOT_FIRST_NAME: Int = 1

        public const val SLOT_LAST_NAME: Int = 2

        public const val SLOT_GENDER: Int = 3

        public const val SLOT_BOOKS: Int = 4

        public val type: ImmutableType = ImmutableType
            .newBuilder(
                "0.8.92",
                Author::class,
                listOf(

                ),
            ) { ctx, base ->
                DraftImpl(ctx, base as Author?)
            }
            .id(SLOT_ID, "id", Long::class.java)
            .key(SLOT_FIRST_NAME, "firstName", String::class.java, false)
            .key(SLOT_LAST_NAME, "lastName", String::class.java, false)
            .add(SLOT_GENDER, "gender", ImmutablePropCategory.SCALAR, Gender::class.java, false)
            .add(SLOT_BOOKS, "books", ManyToMany::class.java, Book::class.java, false)
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
                SLOT_ID ->
                	id
                SLOT_FIRST_NAME ->
                	firstName
                SLOT_LAST_NAME ->
                	lastName
                SLOT_GENDER ->
                	gender
                SLOT_BOOKS ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
                    prop
                )

            }

            public override fun __get(prop: String): Any? = when (prop) {
                "id" ->
                	id
                "firstName" ->
                	firstName
                "lastName" ->
                	lastName
                "gender" ->
                	gender
                "books" ->
                	books
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
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

            internal var __firstNameValue: String? = null

            internal var __lastNameValue: String? = null

            internal var __genderValue: Gender? = null

            internal var __booksValue: NonSharedList<Book>? = null

            public override val id: Long
                @JsonIgnore
                get() {
                    if (!__idLoaded) {
                        throw UnloadedException(Author::class.java, "id")
                    }
                    return __idValue
                }

            public override val firstName: String
                @JsonIgnore
                get() {
                    val __firstNameValue = this.__firstNameValue
                    if (__firstNameValue === null) {
                        throw UnloadedException(Author::class.java, "firstName")
                    }
                    return __firstNameValue
                }

            public override val lastName: String
                @JsonIgnore
                get() {
                    val __lastNameValue = this.__lastNameValue
                    if (__lastNameValue === null) {
                        throw UnloadedException(Author::class.java, "lastName")
                    }
                    return __lastNameValue
                }

            public override val gender: Gender
                @JsonIgnore
                get() {
                    val __genderValue = this.__genderValue
                    if (__genderValue === null) {
                        throw UnloadedException(Author::class.java, "gender")
                    }
                    return __genderValue
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
                SLOT_ID ->
                	__idLoaded
                SLOT_FIRST_NAME ->
                	__firstNameValue !== null
                SLOT_LAST_NAME ->
                	__lastNameValue !== null
                SLOT_GENDER ->
                	__genderValue !== null
                SLOT_BOOKS ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
                    prop
                )

            }

            public override fun __isLoaded(prop: String): Boolean = when (prop) {
                "id" ->
                	__idLoaded
                "firstName" ->
                	__firstNameValue !== null
                "lastName" ->
                	__lastNameValue !== null
                "gender" ->
                	__genderValue !== null
                "books" ->
                	__booksValue !== null
                else -> throw IllegalArgumentException(
                    "Illegal property name" + 
                    " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
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
                    SLOT_FIRST_NAME ->
                    	__visibility.visible(SLOT_FIRST_NAME)
                    SLOT_LAST_NAME ->
                    	__visibility.visible(SLOT_LAST_NAME)
                    SLOT_GENDER ->
                    	__visibility.visible(SLOT_GENDER)
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
                    "firstName" ->
                    	__visibility.visible(SLOT_FIRST_NAME)
                    "lastName" ->
                    	__visibility.visible(SLOT_LAST_NAME)
                    "gender" ->
                    	__visibility.visible(SLOT_GENDER)
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
                if (__firstNameValue !== null) {
                    hash = 31 * hash + __firstNameValue.hashCode()
                }
                if (__lastNameValue !== null) {
                    hash = 31 * hash + __lastNameValue.hashCode()
                }
                if (__genderValue !== null) {
                    hash = 31 * hash + __genderValue.hashCode()
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
                if (__firstNameValue !== null) {
                    hash = 31 * hash + __firstNameValue.hashCode()
                }
                if (__lastNameValue !== null) {
                    hash = 31 * hash + __lastNameValue.hashCode()
                }
                if (__genderValue !== null) {
                    hash = 31 * hash + __genderValue.hashCode()
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
                if (__isVisible(PropId.byIndex(SLOT_FIRST_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_FIRST_NAME))) {
                    return false
                }
                val __firstNameLoaded = 
                    this.__firstNameValue !== null
                if (__firstNameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_FIRST_NAME)))) {
                    return false
                }
                if (__firstNameLoaded && this.__firstNameValue != __other.firstName) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_LAST_NAME))) {
                    return false
                }
                val __lastNameLoaded = 
                    this.__lastNameValue !== null
                if (__lastNameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_LAST_NAME)))) {
                    return false
                }
                if (__lastNameLoaded && this.__lastNameValue != __other.lastName) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_GENDER)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_GENDER))) {
                    return false
                }
                val __genderLoaded = 
                    this.__genderValue !== null
                if (__genderLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_GENDER)))) {
                    return false
                }
                if (__genderLoaded && this.__genderValue != __other.gender) {
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
                if (__isVisible(PropId.byIndex(SLOT_FIRST_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_FIRST_NAME))) {
                    return false
                }
                val __firstNameLoaded = 
                    this.__firstNameValue !== null
                if (__firstNameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_FIRST_NAME)))) {
                    return false
                }
                if (__firstNameLoaded && this.__firstNameValue != __other.firstName) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_NAME)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_LAST_NAME))) {
                    return false
                }
                val __lastNameLoaded = 
                    this.__lastNameValue !== null
                if (__lastNameLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_LAST_NAME)))) {
                    return false
                }
                if (__lastNameLoaded && this.__lastNameValue != __other.lastName) {
                    return false
                }
                if (__isVisible(PropId.byIndex(SLOT_GENDER)) !=
                        __other.__isVisible(PropId.byIndex(SLOT_GENDER))) {
                    return false
                }
                val __genderLoaded = 
                    this.__genderValue !== null
                if (__genderLoaded != (__other.__isLoaded(PropId.byIndex(SLOT_GENDER)))) {
                    return false
                }
                if (__genderLoaded && this.__genderValue != __other.gender) {
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

            public override var id: Long
                @JsonIgnore
                get() = (__modified ?: __base!!).id
                set(id) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__idValue = id
                    __tmpModified.__idLoaded = true
                }

            public override var firstName: String
                @JsonIgnore
                get() = (__modified ?: __base!!).firstName
                set(firstName) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__firstNameValue = firstName
                }

            public override var lastName: String
                @JsonIgnore
                get() = (__modified ?: __base!!).lastName
                set(lastName) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__lastNameValue = lastName
                }

            public override var gender: Gender
                @JsonIgnore
                get() = (__modified ?: __base!!).gender
                set(gender) {
                    val __tmpModified = (__modified ?: __base!!.clone())
                            .also { __modified = it }
                    __tmpModified.__genderValue = gender
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
                    SLOT_FIRST_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__firstNameValue = null
                    SLOT_LAST_NAME ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastNameValue = null
                    SLOT_GENDER ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__genderValue = null
                    SLOT_BOOKS ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
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
                    "firstName" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__firstNameValue = null
                    "lastName" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__lastNameValue = null
                    "gender" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__genderValue = null
                    "books" ->
                    	(__modified ?: __base!!.clone())
                                .also { __modified = it }
                                .__booksValue = null
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
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
                    SLOT_FIRST_NAME ->
                    	this.firstName = value as String?
                    	?: throw IllegalArgumentException("'firstName cannot be null")
                    SLOT_LAST_NAME ->
                    	this.lastName = value as String?
                    	?: throw IllegalArgumentException("'lastName cannot be null")
                    SLOT_GENDER ->
                    	this.gender = value as Gender?
                    	?: throw IllegalArgumentException("'gender cannot be null")
                    SLOT_BOOKS ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
                        prop
                    )

                }
            }

            public override fun __set(prop: String, `value`: Any?): Unit {
                when (prop) {
                    "id" ->
                    	this.id = value as Long?
                    	?: throw IllegalArgumentException("'id cannot be null")
                    "firstName" ->
                    	this.firstName = value as String?
                    	?: throw IllegalArgumentException("'firstName cannot be null")
                    "lastName" ->
                    	this.lastName = value as String?
                    	?: throw IllegalArgumentException("'lastName cannot be null")
                    "gender" ->
                    	this.gender = value as Gender?
                    	?: throw IllegalArgumentException("'gender cannot be null")
                    "books" ->
                    	this.books = value as List<Book>?
                    	?: throw IllegalArgumentException("'books cannot be null")
                    else -> throw IllegalArgumentException(
                        "Illegal property name" + 
                        " for \"org.babyfish.jimmer.example.save.model.Author\": " + 
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
                    SLOT_ID ->
                    	__visibility.show(SLOT_ID, visible)
                    SLOT_FIRST_NAME ->
                    	__visibility.show(SLOT_FIRST_NAME, visible)
                    SLOT_LAST_NAME ->
                    	__visibility.show(SLOT_LAST_NAME, visible)
                    SLOT_GENDER ->
                    	__visibility.show(SLOT_GENDER, visible)
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
                        Visibility.of(5).also{
                            (__modified ?: __base!!.clone())
                            .also { __modified = it }.__visibility = it}
                    }
                    ?: return
                when (prop) {
                    "id" ->
                    	__visibility.show(SLOT_ID, visible)
                    "firstName" ->
                    	__visibility.show(SLOT_FIRST_NAME, visible)
                    "lastName" ->
                    	__visibility.show(SLOT_LAST_NAME, visible)
                    "gender" ->
                    	__visibility.show(SLOT_GENDER, visible)
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

        private var firstName: String? = null

        private var lastName: String? = null

        private var gender: Gender? = null

        private var books: List<Book>? = null

        public fun id(id: Long?): MapStruct {
            this.id = id
            return this
        }

        public fun firstName(firstName: String?): MapStruct {
            this.firstName = firstName
            return this
        }

        public fun lastName(lastName: String?): MapStruct {
            this.lastName = lastName
            return this
        }

        public fun gender(gender: Gender?): MapStruct {
            this.gender = gender
            return this
        }

        public fun books(books: List<Book>?): MapStruct {
            this.books = books ?: emptyList()
            return this
        }

        public fun build(): Author = AuthorDraft
        .`$`.produce {val __that = this@MapStruct
            __that.id?.let  {
                id = it
            }
            __that.firstName?.let  {
                firstName = it
            }
            __that.lastName?.let  {
                lastName = it
            }
            __that.gender?.let  {
                gender = it
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
