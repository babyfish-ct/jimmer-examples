@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = Author::class)

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

public val KNonNullProps<Author>.id: KNonNullPropExpression<Long>
    get() = get<Long>(AuthorProps.ID.unwrap()) as KNonNullPropExpression<Long>

public val KNullableProps<Author>.id: KNullablePropExpression<Long>
    get() = get<Long>(AuthorProps.ID.unwrap()) as KNullablePropExpression<Long>

public val KNonNullProps<Author>.firstName: KNonNullPropExpression<String>
    get() = get<String>(AuthorProps.FIRST_NAME.unwrap()) as KNonNullPropExpression<String>

public val KNullableProps<Author>.firstName: KNullablePropExpression<String>
    get() = get<String>(AuthorProps.FIRST_NAME.unwrap()) as KNullablePropExpression<String>

public val KNonNullProps<Author>.lastName: KNonNullPropExpression<String>
    get() = get<String>(AuthorProps.LAST_NAME.unwrap()) as KNonNullPropExpression<String>

public val KNullableProps<Author>.lastName: KNullablePropExpression<String>
    get() = get<String>(AuthorProps.LAST_NAME.unwrap()) as KNullablePropExpression<String>

public val KNonNullProps<Author>.gender: KNonNullPropExpression<Gender>
    get() = get<Gender>(AuthorProps.GENDER.unwrap()) as KNonNullPropExpression<Gender>

public val KNullableProps<Author>.gender: KNullablePropExpression<Gender>
    get() = get<Gender>(AuthorProps.GENDER.unwrap()) as KNullablePropExpression<Gender>

public fun KProps<Author>.books(block: KNonNullTableEx<Book>.() -> KNonNullExpression<Boolean>?):
        KNonNullExpression<Boolean>? = exists(AuthorProps.BOOKS.unwrap(), block)

public val KTableEx<Author>.books: KNonNullTableEx<Book>
    get() = join(AuthorProps.BOOKS.unwrap())

public val KTableEx<Author>.`books?`: KNullableTableEx<Book>
    get() = outerJoin(AuthorProps.BOOKS.unwrap())

public val KRemoteRef.NonNull<Author>.id: KNonNullPropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNonNullPropExpression<Long>

public val KRemoteRef.Nullable<Author>.id: KNullablePropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNullablePropExpression<Long>

public fun KNonNullTable<Author>.fetchBy(block: AuthorFetcherDsl.() -> Unit): Selection<Author> =
        fetch(newFetcher(Author::class).`by`(block))

public fun KNullableTable<Author>.fetchBy(block: AuthorFetcherDsl.() -> Unit): Selection<Author?> =
        fetch(newFetcher(Author::class).`by`(block))

public object AuthorProps {
    public val ID: TypedProp.Scalar<Author, Long> = TypedProp.scalar(Author::id.toImmutableProp())

    public val FIRST_NAME: TypedProp.Scalar<Author, String> =
            TypedProp.scalar(Author::firstName.toImmutableProp())

    public val LAST_NAME: TypedProp.Scalar<Author, String> =
            TypedProp.scalar(Author::lastName.toImmutableProp())

    public val GENDER: TypedProp.Scalar<Author, Gender> =
            TypedProp.scalar(Author::gender.toImmutableProp())

    public val BOOKS: TypedProp.ReferenceList<Author, Book> =
            TypedProp.referenceList(Author::books.toImmutableProp())
}
