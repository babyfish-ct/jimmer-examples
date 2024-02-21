@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = BookStore::class)

package org.babyfish.jimmer.example.save.model

import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import org.babyfish.jimmer.`internal`.GeneratedBy
import org.babyfish.jimmer.kt.DslScope
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.fetcher.`impl`.FetcherImpl
import org.babyfish.jimmer.sql.kt.fetcher.FetcherCreator
import org.babyfish.jimmer.sql.kt.fetcher.KListFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.`impl`.JavaFieldConfigUtils
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

public fun FetcherCreator<BookStore>.`by`(block: BookStoreFetcherDsl.() -> Unit):
        Fetcher<BookStore> {
    val dsl = BookStoreFetcherDsl(emptyBookStoreFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

public fun FetcherCreator<BookStore>.`by`(base: Fetcher<BookStore>?,
        block: BookStoreFetcherDsl.() -> Unit): Fetcher<BookStore> {
    val dsl = BookStoreFetcherDsl(base ?: emptyBookStoreFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

@DslScope
public class BookStoreFetcherDsl(
    fetcher: Fetcher<BookStore>,
) {
    private var _fetcher: Fetcher<BookStore> = fetcher

    public fun internallyGetFetcher(): Fetcher<BookStore> = _fetcher

    public fun allScalarFields(): Unit {
        _fetcher = _fetcher.allScalarFields()
    }

    public fun allTableFields(): Unit {
        _fetcher = _fetcher.allTableFields()
    }

    public fun name(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("name")
        } else {
            _fetcher.remove("name")
        }
    }

    public fun website(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("website")
        } else {
            _fetcher.remove("website")
        }
    }

    public fun books(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("books")
        } else {
            _fetcher.remove("books")
        }
    }

    public fun books(childFetcher: Fetcher<Book>, cfgBlock: (KListFieldDsl<Book>.() -> Unit)? =
            null): Unit {
        _fetcher = _fetcher.add(
            "books",
            childFetcher,
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun books(cfgBlock: (KListFieldDsl<Book>.() -> Unit)? = null,
            childBlock: BookFetcherDsl.() -> Unit): Unit {
        _fetcher = _fetcher.add(
            "books",
            newFetcher(Book::class).by(childBlock),
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun books(
        enabled: Boolean,
        childFetcher: Fetcher<Book>,
        cfgBlock: (KListFieldDsl<Book>.() -> Unit)? = null,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("books")
        } else {
            books(childFetcher, cfgBlock)
        }
    }

    public fun books(
        enabled: Boolean,
        cfgBlock: (KListFieldDsl<Book>.() -> Unit)? = null,
        childBlock: BookFetcherDsl.() -> Unit,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("books")
        } else {
            books(cfgBlock, childBlock)
        }
    }
}

private val emptyBookStoreFetcher: Fetcher<BookStore> = FetcherImpl(BookStore::class.java)
