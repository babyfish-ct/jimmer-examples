package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@KeyUniqueConstraint
public interface TreeNode {

    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    /**
     * The name of current TreeNode.
     *
     * <p>This property forms a unique constraint,
     * which is {@code @Key} of Jimmer</p>
     */
    @Key
    String name();

    /**
     * The parent node of the current TreeNode.
     * The type of the associated object is the
     * same as the current object type, so it
     * is a self-associated property that can
     * be queried recursively.
     */
    @Nullable
    @Key
    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    TreeNode parent();

    /**
     * The child nodes of the current TreeNode.
     * The type of the associated object is the
     * same as the current object type, so it
     * is a self-associated property that can
     * be queried recursively.
     */
    @OneToMany(mappedBy = "parent", orderedProps = @OrderedProp("name"))
    List<TreeNode> childNodes();
}