package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*
import java.math.BigDecimal

@Entity
@KeyUniqueConstraint
interface Book {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     * The name of current Book.
     *
     * This property forms a unique constraint together with
     * the [edition] property, which is `@Key` of Jimmer
     */
    @Key
    val name: String

    /**
     * The edition of current Book.
     *
     * This property forms a unique constraint together with
     * the [name] property, which is `@Key` of Jimmer
     */
    @Key
    val edition: Int

    val price: BigDecimal

    /**
     * The bookstore to which the current book belongs,
     * representing a many-to-one association
     */
    @ManyToOne
    @OnDissociate(DissociateAction.SET_NULL)
    val store: BookStore?

    /**
     * All authors who participated in writing
     * the current book,
     * representing a many-to-many association
     */
    @ManyToMany(orderedProps = [
        OrderedProp("firstName"),
        OrderedProp("lastName")
    ])
    @JoinTable(name = "BOOK_AUTHOR_MAPPING", joinColumnName = "BOOK_ID", inverseJoinColumnName = "AUTHOR_ID")
    val authors: List<Author>
}