@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = BookStore::class)

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

public val KNonNullProps<BookStore>.id: KNonNullPropExpression<Long>
    get() = get<Long>(BookStoreProps.ID.unwrap()) as KNonNullPropExpression<Long>

public val KNullableProps<BookStore>.id: KNullablePropExpression<Long>
    get() = get<Long>(BookStoreProps.ID.unwrap()) as KNullablePropExpression<Long>

public val KNonNullProps<BookStore>.name: KNonNullPropExpression<String>
    get() = get<String>(BookStoreProps.NAME.unwrap()) as KNonNullPropExpression<String>

public val KNullableProps<BookStore>.name: KNullablePropExpression<String>
    get() = get<String>(BookStoreProps.NAME.unwrap()) as KNullablePropExpression<String>

public val KProps<BookStore>.website: KNullablePropExpression<String>
    get() = get<String>(BookStoreProps.WEBSITE.unwrap()) as KNullablePropExpression<String>

public fun KProps<BookStore>.books(block: KNonNullTableEx<Book>.() -> KNonNullExpression<Boolean>?):
        KNonNullExpression<Boolean>? = exists(BookStoreProps.BOOKS.unwrap(), block)

public val KTableEx<BookStore>.books: KNonNullTableEx<Book>
    get() = join(BookStoreProps.BOOKS.unwrap())

public val KTableEx<BookStore>.`books?`: KNullableTableEx<Book>
    get() = outerJoin(BookStoreProps.BOOKS.unwrap())

public val KRemoteRef.NonNull<BookStore>.id: KNonNullPropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNonNullPropExpression<Long>

public val KRemoteRef.Nullable<BookStore>.id: KNullablePropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNullablePropExpression<Long>

public fun KNonNullTable<BookStore>.fetchBy(block: BookStoreFetcherDsl.() -> Unit):
        Selection<BookStore> = fetch(newFetcher(BookStore::class).`by`(block))

public fun KNullableTable<BookStore>.fetchBy(block: BookStoreFetcherDsl.() -> Unit):
        Selection<BookStore?> = fetch(newFetcher(BookStore::class).`by`(block))

public object BookStoreProps {
    public val ID: TypedProp.Scalar<BookStore, Long> =
            TypedProp.scalar(BookStore::id.toImmutableProp())

    public val NAME: TypedProp.Scalar<BookStore, String> =
            TypedProp.scalar(BookStore::name.toImmutableProp())

    public val WEBSITE: TypedProp.Scalar<BookStore, String?> =
            TypedProp.scalar(BookStore::website.toImmutableProp())

    public val BOOKS: TypedProp.ReferenceList<BookStore, Book> =
            TypedProp.referenceList(BookStore::books.toImmutableProp())
}
