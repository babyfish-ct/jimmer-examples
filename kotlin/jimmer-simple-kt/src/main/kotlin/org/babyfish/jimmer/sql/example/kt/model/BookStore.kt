package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*


@Entity
@KeyUniqueConstraint
interface BookStore {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     * The name of current BookStore.
     *
     * This property forms a unique constraint,
     * which is `@Key` of Jimmer
     */
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