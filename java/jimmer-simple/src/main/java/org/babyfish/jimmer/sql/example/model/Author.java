package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;

import java.util.List;

@Entity
@KeyUniqueConstraint
public interface Author {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The first name of current Author.
     *
     * <p>This property forms a unique constraint together with
     * the {@code lastName} property, which is {@code @Key} of Jimmer</p>
     */
    @Key
    String firstName();

    /**
     * The last name of current Book.
     *
     * <p>This property forms a unique constraint together with
     * the {@code firstName} property, which is {@code @Key} of Jimmer</p>
     */
    @Key
    String lastName();

    Gender gender();

    @ManyToMany(mappedBy = "authors", orderedProps = {
            @OrderedProp("name"),
            @OrderedProp(value = "edition", desc = true)
    })
    List<Book> books();
}