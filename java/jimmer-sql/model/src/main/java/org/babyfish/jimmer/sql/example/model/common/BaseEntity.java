package org.babyfish.jimmer.sql.example.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.babyfish.jimmer.sql.MappedSuperclass;

import java.time.LocalDateTime;

/*
 * see CommonEntityDraftInterceptor
 */
@MappedSuperclass // (1)
public interface BaseEntity {

    /**
     * The time when the object was created.
     *
     * <p>In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by {@code DraftInterceptor}</p>
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTime();

    /**
     * The time when the object was last modified
     *
     * <p>In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by {@code DraftInterceptor}</p>
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTime();
}

/*----------------Documentation Links----------------
(1) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/mapped-super-class
---------------------------------------------------*/
