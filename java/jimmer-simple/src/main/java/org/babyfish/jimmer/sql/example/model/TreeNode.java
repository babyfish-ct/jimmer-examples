package org.babyfish.jimmer.sql.example.model;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@KeyUniqueConstraint
public interface TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    @Key
    String name();

    @Nullable
    @Key
    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    TreeNode parent();

    @OneToMany(mappedBy = "parent", orderedProps = @OrderedProp("name"))
    List<TreeNode> childNodes();
}