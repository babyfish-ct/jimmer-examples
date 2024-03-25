package org.babyfish.jimmer.example.kt.core.model

import org.babyfish.jimmer.Immutable
import java.time.LocalDateTime

@Immutable
interface Author {

    val name: String

    val lastModifiedTime: LocalDateTime

    val books: List<Book>
}