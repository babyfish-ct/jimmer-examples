package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*


@Entity
@KeyUniqueConstraint
interface BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key
    val name: String

    val website: String?

    /**
     * All books belonging to the current bookstore,
     * representing a one-to-many association
     */
    @OneToMany(
        mappedBy = "store",
        orderedProps = [
            OrderedProp("name"),
            OrderedProp(value = "edition", desc = true)
        ]
    )
    val books: List<Book>
}