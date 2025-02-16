package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*


@Entity
@KeyUniqueConstraint
interface Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key
    val firstName: String

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