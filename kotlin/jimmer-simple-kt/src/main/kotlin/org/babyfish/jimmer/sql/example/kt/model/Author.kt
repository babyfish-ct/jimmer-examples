package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*


@Entity
@KeyUniqueConstraint
interface Author {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     * The first name of current Author.
     *
     * This property forms a unique constraint together with
     * the [lastName] property, which is `@Key` of Jimmer
     */
    @Key
    val firstName: String

    /**
     * The last name of current Book.
     *
     * This property forms a unique constraint together with
     * the [firstName] property, which is `@Key` of Jimmer
     */
    @Key
    val lastName: String

    val gender: Gender

    @ManyToMany(
        mappedBy = "authors",
        orderedProps = [
            OrderedProp("name"),
            OrderedProp(value = "edition", desc = true)
        ]
    )
    val books: List<Book>
}