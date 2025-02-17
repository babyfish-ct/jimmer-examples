package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@KeyUniqueConstraint
public interface BookStore {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of current BookStore.
     *
     * <p>This property forms a unique constraint,
     * which is {@code @Key} of Jimmer</p>
     */
    @Key
    String name();

    @Nullable
    String website();

    /**
     * All books belonging to the current bookstore,
     * representing a one-to-many association
     */
    @OneToMany(mappedBy = "store", orderedProps = {
            @OrderedProp("name"),
            @OrderedProp(value = "edition", desc = true)
    })
    List<Book> books();
}
