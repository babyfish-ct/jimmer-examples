@file:Suppress(
    "RedundantVisibilityModifier",
    "Unused",
)
@file:GeneratedBy(type = Book::class)

package org.babyfish.jimmer.example.save.model

import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import org.babyfish.jimmer.`internal`.GeneratedBy
import org.babyfish.jimmer.kt.DslScope
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.fetcher.IdOnlyFetchType
import org.babyfish.jimmer.sql.fetcher.`impl`.FetcherImpl
import org.babyfish.jimmer.sql.kt.fetcher.FetcherCreator
import org.babyfish.jimmer.sql.kt.fetcher.KFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.KListFieldDsl
import org.babyfish.jimmer.sql.kt.fetcher.`impl`.JavaFieldConfigUtils
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

public fun FetcherCreator<Book>.`by`(block: BookFetcherDsl.() -> Unit): Fetcher<Book> {
    val dsl = BookFetcherDsl(emptyBookFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

public fun FetcherCreator<Book>.`by`(base: Fetcher<Book>?, block: BookFetcherDsl.() -> Unit):
        Fetcher<Book> {
    val dsl = BookFetcherDsl(base ?: emptyBookFetcher)
    dsl.block()
    return dsl.internallyGetFetcher()
}

@DslScope
public class BookFetcherDsl(
    fetcher: Fetcher<Book>,
) {
    private var _fetcher: Fetcher<Book> = fetcher

    public fun internallyGetFetcher(): Fetcher<Book> = _fetcher

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

    public fun edition(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("edition")
        } else {
            _fetcher.remove("edition")
        }
    }

    public fun price(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("price")
        } else {
            _fetcher.remove("price")
        }
    }

    public fun store(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("store")
        } else {
            _fetcher.remove("store")
        }
    }

    public fun store(childFetcher: Fetcher<BookStore>, cfgBlock: (KFieldDsl<BookStore>.() -> Unit)?
            = null): Unit {
        _fetcher = _fetcher.add(
            "store",
            childFetcher,
            JavaFieldConfigUtils.simple(cfgBlock)
        )
    }

    public fun store(cfgBlock: (KFieldDsl<BookStore>.() -> Unit)? = null,
            childBlock: BookStoreFetcherDsl.() -> Unit): Unit {
        _fetcher = _fetcher.add(
            "store",
            newFetcher(BookStore::class).by(childBlock),
            JavaFieldConfigUtils.simple(cfgBlock)
        )
    }

    public fun store(
        enabled: Boolean,
        childFetcher: Fetcher<BookStore>,
        cfgBlock: (KFieldDsl<BookStore>.() -> Unit)? = null,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("store")
        } else {
            store(childFetcher, cfgBlock)
        }
    }

    public fun store(
        enabled: Boolean,
        cfgBlock: (KFieldDsl<BookStore>.() -> Unit)? = null,
        childBlock: BookStoreFetcherDsl.() -> Unit,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("store")
        } else {
            store(cfgBlock, childBlock)
        }
    }

    public fun store(idOnlyFetchType: IdOnlyFetchType): Unit {
        _fetcher = _fetcher.add("store", idOnlyFetchType)
    }

    public fun authors(enabled: Boolean = true): Unit {
        _fetcher = if (enabled) {
            _fetcher.add("authors")
        } else {
            _fetcher.remove("authors")
        }
    }

    public fun authors(childFetcher: Fetcher<Author>, cfgBlock: (KListFieldDsl<Author>.() -> Unit)?
            = null): Unit {
        _fetcher = _fetcher.add(
            "authors",
            childFetcher,
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun authors(cfgBlock: (KListFieldDsl<Author>.() -> Unit)? = null,
            childBlock: AuthorFetcherDsl.() -> Unit): Unit {
        _fetcher = _fetcher.add(
            "authors",
            newFetcher(Author::class).by(childBlock),
            JavaFieldConfigUtils.list(cfgBlock)
        )
    }

    public fun authors(
        enabled: Boolean,
        childFetcher: Fetcher<Author>,
        cfgBlock: (KListFieldDsl<Author>.() -> Unit)? = null,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("authors")
        } else {
            authors(childFetcher, cfgBlock)
        }
    }

    public fun authors(
        enabled: Boolean,
        cfgBlock: (KListFieldDsl<Author>.() -> Unit)? = null,
        childBlock: AuthorFetcherDsl.() -> Unit,
    ): Unit {
        if (!enabled) {
            _fetcher = _fetcher.remove("authors")
        } else {
            authors(cfgBlock, childBlock)
        }
    }

    public fun authors(idOnlyFetchType: IdOnlyFetchType): Unit {
        _fetcher = _fetcher.add("authors", idOnlyFetchType)
    }
}

private val emptyBookFetcher: Fetcher<Book> = FetcherImpl(Book::class.java)
