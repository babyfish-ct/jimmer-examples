package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.example.kt.model.common.BaseEntity

/*
 * In this example, `@KeyConstraint` will not take effect,
 * meaning it won't utilize the database's upsert capability.
 * Instead, it will use a select query to determine whether
 * the subsequent operation should be an insert or update.
 *
 * This is due to:
 *
 * 1. For the root object being saved, the use of
 *    `DraftInterceptor` will trigger a query
 *
 * 2. For the associated child objects being saved, in
 *    addition to reason #1, there's also the fact that
 *    by default, the `Transferable` capability of child
 *    objects is not enabled. This means that by default,
 *    a parent object cannot take child objects from
 *    other parent objects.
 *
 * However, in actual projects, it is still recommended
 * to specify `@KeyConstraint` for each entity.
 */
@Entity
@KeyUniqueConstraint(
    // Only for mysql
    noMoreUniqueConstraints = true,
    // Only for postgres
    isNullNotDistinct = true
)
interface Author : BaseEntity {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    // It is inappropriate to use `firstName` and `lastName`
    // as keys in actual project, but this is just a small demo.

    @Key // (1)
    val firstName: String

    @Key // (2)
    val lastName: String

    val gender: Gender

    @ManyToMany(mappedBy = "authors") // (3)
    val books: List<Book>

    // -----------------------------
    //
    // Everything below this line are calculated properties.
    //
    // The simple calculated properties are shown here. As for the
    // complex calculated properties, you can view `BookStore.avgPrice` and
    // `BookStore.newestBooks`
    // -----------------------------

    // -----------------------------
    //
    // Everything below this line are calculated properties.
    //
    // The simple calculated properties are shown here. As for the
    // complex calculated properties, you can view `BookStore.avgPrice` and
    // `BookStore.newestBooks`
    // -----------------------------
    @Formula(dependencies = ["firstName", "lastName"]) // (4)
    val fullName: String
        get() = "$firstName $lastName"

    // The simple property above is simple calculation based on JAVA expression,
    // you can also define simple calculations given SQL expressions like this
    //
    // @Formula(sql = "concat(%alias.FIRST_NAME, ' ', %alias.LAST_NAME)")
    // val fullName
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/formula
---------------------------------------------------*/
