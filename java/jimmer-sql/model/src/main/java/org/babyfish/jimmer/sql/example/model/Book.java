package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.babyfish.jimmer.sql.example.model.common.BaseEntity;
import org.babyfish.jimmer.sql.example.model.common.TenantAware;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

@Entity
public interface Book extends BaseEntity, TenantAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of Book, a property of Natural key `[name, edition]`
     */
    @Key // (1)
    String name();

    /**
     * The edition of Book, a property of Natural key `[name, edition]`
     */
    @Key // (2)
    int edition();

    BigDecimal price();

    @Nullable // (3) Null property, Java API requires this annotation, but kotlin API does not
    @ManyToOne // (4)
    BookStore store();

    @ManyToMany(orderedProps = { // (5)
            @OrderedProp("firstName"),
            @OrderedProp("lastName")
    })
    @JoinTable(
            name = "BOOK_AUTHOR_MAPPING",
            joinColumnName = "BOOK_ID",
            inverseJoinColumnName = "AUTHOR_ID"
    )
    List<Author> authors();

    // -----------------------------
    // Optional properties
    // -----------------------------

    /**
     * Optional id-view property `storeId`
     *
     * <p>For reference association, even if this id-view property is not declared,
     * the SQL DSL can still use the associated id property "storeId"</p>
     */
    @IdView  // (6)
    Long storeId();

    /**
     * Optional id-view property `authorIds`
     */
    @IdView("authors") // (7)
    List<Long> authorIds();
}

/*----------------Documentation Links----------------
(1) (2) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/key
(3) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/nullity
(4) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-one
(5) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/base/association/many-to-many
(6) (7) https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/view/id-view
---------------------------------------------------*/
