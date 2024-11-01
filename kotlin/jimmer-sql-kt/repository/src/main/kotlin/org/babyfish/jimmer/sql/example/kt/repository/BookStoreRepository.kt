package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.example.kt.model.BookStore
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Repository

@Repository
class BookStoreRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<BookStore, Long>(sql)
