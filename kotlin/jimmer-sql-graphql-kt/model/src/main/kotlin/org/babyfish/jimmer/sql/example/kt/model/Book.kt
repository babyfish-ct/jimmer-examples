package org.babyfish.jimmer.sql.example.kt.model

import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.example.kt.model.common.BaseEntity
import org.babyfish.jimmer.sql.example.kt.model.common.TenantAware
import java.math.BigDecimal

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
interface Book : BaseEntity, TenantAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    @Key // (1)
    val name: String

    @Key // (2)
    val edition: Int
    val price: BigDecimal

    @ManyToOne // (3)
    @OnDissociate(DissociateAction.SET_NULL)
    val store: BookStore?

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
