package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.EnumItem;

public enum Gender {

    @EnumItem(name = "M")
    MALE,

    @EnumItem(name = "F")
    FEMALE
}

/*----------------Documentation Links----------------
❶ ❷ https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/enum
---------------------------------------------------*/
