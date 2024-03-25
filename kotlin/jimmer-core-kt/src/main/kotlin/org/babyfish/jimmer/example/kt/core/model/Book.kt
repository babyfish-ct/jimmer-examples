package org.babyfish.jimmer.example.kt.core.model

import org.babyfish.jimmer.Immutable
import java.time.LocalDateTime

@Immutable
interface Book {

    val name: String

    val store: BookStore?

    val price: Int

    val lastModifiedTime: LocalDateTime

    val authors: List<Author>
}