@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = Book::class)

package org.babyfish.jimmer.example.save.model

import java.math.BigDecimal
import kotlin.Boolean
import kotlin.Int
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

public val KNonNullProps<Book>.id: KNonNullPropExpression<Long>
    get() = get<Long>(BookProps.ID.unwrap()) as KNonNullPropExpression<Long>

public val KNullableProps<Book>.id: KNullablePropExpression<Long>
    get() = get<Long>(BookProps.ID.unwrap()) as KNullablePropExpression<Long>

public val KNonNullProps<Book>.name: KNonNullPropExpression<String>
    get() = get<String>(BookProps.NAME.unwrap()) as KNonNullPropExpression<String>

public val KNullableProps<Book>.name: KNullablePropExpression<String>
    get() = get<String>(BookProps.NAME.unwrap()) as KNullablePropExpression<String>

public val KNonNullProps<Book>.edition: KNonNullPropExpression<Int>
    get() = get<Int>(BookProps.EDITION.unwrap()) as KNonNullPropExpression<Int>

public val KNullableProps<Book>.edition: KNullablePropExpression<Int>
    get() = get<Int>(BookProps.EDITION.unwrap()) as KNullablePropExpression<Int>

public val KNonNullProps<Book>.price: KNonNullPropExpression<BigDecimal>
    get() = get<BigDecimal>(BookProps.PRICE.unwrap()) as KNonNullPropExpression<BigDecimal>

public val KNullableProps<Book>.price: KNullablePropExpression<BigDecimal>
    get() = get<BigDecimal>(BookProps.PRICE.unwrap()) as KNullablePropExpression<BigDecimal>

public val KProps<Book>.store: KNonNullTable<BookStore>
    get() = join(BookProps.STORE.unwrap())

public val KProps<Book>.`store?`: KNullableTable<BookStore>
    get() = outerJoin(BookProps.STORE.unwrap())

public val KTableEx<Book>.store: KNonNullTableEx<BookStore>
    get() = join(BookProps.STORE.unwrap())

public val KTableEx<Book>.`store?`: KNullableTableEx<BookStore>
    get() = outerJoin(BookProps.STORE.unwrap())

public val KProps<Book>.storeId: KNullablePropExpression<Long>
    get() = getAssociatedId<Long>(BookProps.STORE.unwrap()) as KNullablePropExpression<Long>

public fun KProps<Book>.authors(block: KNonNullTableEx<Author>.() -> KNonNullExpression<Boolean>?):
        KNonNullExpression<Boolean>? = exists(BookProps.AUTHORS.unwrap(), block)

public val KTableEx<Book>.authors: KNonNullTableEx<Author>
    get() = join(BookProps.AUTHORS.unwrap())

public val KTableEx<Book>.`authors?`: KNullableTableEx<Author>
    get() = outerJoin(BookProps.AUTHORS.unwrap())

public val KRemoteRef.NonNull<Book>.id: KNonNullPropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNonNullPropExpression<Long>

public val KRemoteRef.Nullable<Book>.id: KNullablePropExpression<Long>
    get() = (this as KRemoteRefImplementor<*>).id<Long>() as KNullablePropExpression<Long>

public fun KNonNullTable<Book>.fetchBy(block: BookFetcherDsl.() -> Unit): Selection<Book> =
        fetch(newFetcher(Book::class).`by`(block))

public fun KNullableTable<Book>.fetchBy(block: BookFetcherDsl.() -> Unit): Selection<Book?> =
        fetch(newFetcher(Book::class).`by`(block))

public object BookProps {
    public val ID: TypedProp.Scalar<Book, Long> = TypedProp.scalar(Book::id.toImmutableProp())

    public val NAME: TypedProp.Scalar<Book, String> = TypedProp.scalar(Book::name.toImmutableProp())

    public val EDITION: TypedProp.Scalar<Book, Int> =
            TypedProp.scalar(Book::edition.toImmutableProp())

    public val PRICE: TypedProp.Scalar<Book, BigDecimal> =
            TypedProp.scalar(Book::price.toImmutableProp())

    public val STORE: TypedProp.Reference<Book, BookStore?> =
            TypedProp.reference(Book::store.toImmutableProp())

    public val AUTHORS: TypedProp.ReferenceList<Book, Author> =
            TypedProp.referenceList(Book::authors.toImmutableProp())
}
