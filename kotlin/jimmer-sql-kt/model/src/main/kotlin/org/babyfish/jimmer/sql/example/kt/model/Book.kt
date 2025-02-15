package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.example.kt.model.common.BaseEntity
import org.babyfish.jimmer.sql.example.kt.model.common.TenantAware
import java.math.BigDecimal

@Entity
interface Book : BaseEntity, TenantAware {

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
     * the `edition` property, which is `@Key` of Jimmer
     */
    @Key // (1)
    val name: String

    /**
     * The edition of current Book.
     *
     * This property forms a unique constraint together with
     * the `name` property, which is `@Key` of Jimmer
     */
    @Key // (2)
    val edition: Int

    val price: BigDecimal

    /**
     * The bookstore to which the current book belongs,
     * representing a many-to-one association
     */
    @ManyToOne // (3)
    val store: BookStore?

    /**
     * All authors who participated in writing
     * the current book,
     * representing a many-to-many association
     */
    @ManyToMany // (4)
    @JoinTable(
        name = "BOOK_AUTHOR_MAPPING",
        joinColumnName = "BOOK_ID",
        inverseJoinColumnName = "AUTHOR_ID"
    )
    val authors: List<Author>

    // -----------------------------
    // Optional properties
    // -----------------------------

    // Optional property `storeId`
    // If this property is deleted, please add `BookInput.Mapper.toBookStore(Long)`
    @IdView // (5)
    val storeId: Long?

    // Optional property `authorIds`
    // If this property is deleted, please add `BookInputMapper.toAuthor(Long)`
    @IdView("authors") // (6)
    val authorIds: List<Long>
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(5) (6) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/view/id-view
---------------------------------------------------*/
