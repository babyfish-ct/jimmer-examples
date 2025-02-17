package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

@Entity
@KeyUniqueConstraint
public interface Book {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of current Book.
     *
     * <p>This property forms a unique constraint together with
     * the {@code edition} property, which is {@code @Key} of Jimmer</p>
     */
    @Key
    String name();

    /**
     * The edition of current Book.
     *
     * <p>This property forms a unique constraint together with
     * the {@code name} property, which is {@code @Key} of Jimmer</p>
     */
    @Key
    int edition();

    BigDecimal price();

    /**
     * The bookstore to which the current book belongs,
     * representing a many-to-one association
     */
    @Nullable
    @ManyToOne
    @OnDissociate(DissociateAction.SET_NULL)
    BookStore store();

    /**
     * All authors who participated in writing
     * the current book,
     * representing a many-to-many association
     */
    @ManyToMany(orderedProps = {
            @OrderedProp("firstName"),
            @OrderedProp("lastName")
    })
    @JoinTable(
            name = "BOOK_AUTHOR_MAPPING",
            joinColumnName = "BOOK_ID",
            inverseJoinColumnName = "AUTHOR_ID"
    )
    List<Author> authors();
}
