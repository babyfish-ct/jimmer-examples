package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.fetcher.Fetcher

interface BookStoreRepository : KRepository<BookStore, Long> {
    fun findByNameLikeOrderByName(name: String?, fetcher: Fetcher<BookStore>?): List<BookStore>
}

