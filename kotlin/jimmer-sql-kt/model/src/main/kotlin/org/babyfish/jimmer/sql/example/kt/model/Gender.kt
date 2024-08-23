package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.EnumItem

enum class Gender {

    @EnumItem(name = "M") // ❶
    MALE,

    @EnumItem(name = "F") // ❷
    FEMALE
}

/*----------------Documentation Links----------------
https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/enum
---------------------------------------------------*/