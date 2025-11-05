package org.babyfish.jimmer.sql.example.kt.model.common

import org.babyfish.jimmer.sql.MappedSuperclass

@MappedSuperclass
interface TenantAware {

    /**
     * The tenant to which the current object belongs.
     *
     * For the database in this example,
     * there are two values: `a` and `b`.
     *
     * In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by `DraftInterceptor`
     */
    val tenant: String
}