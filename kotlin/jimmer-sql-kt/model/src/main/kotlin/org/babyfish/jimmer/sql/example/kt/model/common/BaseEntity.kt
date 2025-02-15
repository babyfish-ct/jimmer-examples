package org.babyfish.jimmer.sql.example.kt.model.common

import com.fasterxml.jackson.annotation.JsonFormat
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

/*
 * see CommonEntityDraftInterceptor
 */
@MappedSuperclass
interface BaseEntity {

    /**
     * The time when the object was created.
     *
     * In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by `DraftInterceptor`
     */
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime

    /**
     * The time when the object was last modified
     *
     * In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by `DraftInterceptor`
     */
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val modifiedTime: LocalDateTime
}