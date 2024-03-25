package org.babyfish.jimmer.example.kt.core.model

import org.babyfish.jimmer.Immutable
import java.time.LocalDateTime

@Immutable
interface BookStore {

    val name: String

    val lastModifiedTime: LocalDateTime

    val books: List<Book>
}