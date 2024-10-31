package org.babyfish.jimmer.example.save.model

import org.babyfish.jimmer.sql.*
import java.math.BigDecimal

@Entity
@KeyUniqueConstraint
interface Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key
    val name: String

    @Key
    val edition: Int

    val price: BigDecimal

    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    val store: BookStore?

    @ManyToMany
    @JoinTable(
        name = "BOOK_AUTHOR_MAPPING",
        joinColumnName = "BOOK_ID",
        inverseJoinColumnName = "AUTHOR_ID"
    )
    val authors: List<Author>
}