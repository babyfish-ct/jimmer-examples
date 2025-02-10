package org.babyfish.jimmer.sql.example.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.babyfish.jimmer.sql.MappedSuperclass;

import java.time.LocalDateTime;

/*
 * see CommonEntityDraftInterceptor
 */
@MappedSuperclass // (1)
public interface BaseEntity {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTime();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTime();
}

/*----------------Documentation Links----------------
(1) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/mapped-super-class
---------------------------------------------------*/
