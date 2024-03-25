package org.babyfish.jimmer.example.core.model;

import org.babyfish.jimmer.Immutable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Immutable
public interface TreeNode {

    String name();

    @Nullable
    TreeNode parent();

    List<TreeNode> childNodes();
}
