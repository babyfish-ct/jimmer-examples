package org.babyfish.jimmer.sql.example.model.common;

import org.babyfish.jimmer.sql.MappedSuperclass;

@MappedSuperclass // (1)
public interface TenantAware {

    /**
     * The tenant to which the current object belongs.
     *
     * <p>For the database in this example,
     * there are two values: {@code a} and {code b}</p>.
     *
     * <p>In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by {@code DraftInterceptor}</p>
     */
    String tenant();
}

/*----------------Documentation Links----------------
(1) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/mapped-super-class
---------------------------------------------------*/
