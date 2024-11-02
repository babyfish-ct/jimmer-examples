package org.babyfish.jimmer.sql.example.repository;

import org.babyfish.jimmer.View;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.TreeNode;
import org.babyfish.jimmer.sql.example.model.TreeNodeTable;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreeNodeRepository extends AbstractJavaRepository<TreeNode, Long> {

    private static final TreeNodeTable table = TreeNodeTable.$;

    public TreeNodeRepository(JSqlClient sql) {
        super(sql);
    }

    /*
     * This approach is very special, the rest query methods of the project returns 'dynamic object + @FetchBy',
     * but it directly returns static types which should be generated at compilation-time.
     *
     * In fact, you can also define this method as:
     * List<FlatTreeNodeView> findByNameLikeIgnoreCase(@Nullable String name)
     *
     * However, a better development experience is to determine the shape of the data structure
     * at the business layer, not the data layer. So, let's define the parameter `viewType`
     */
    public <V extends View<TreeNode>> List<V> findByNameLikeIgnoreCase(
            @Nullable String name,
            Class<V> viewType
    ) {
        return sql
                .createQuery(table)
                .where(table.name().likeIf(name))
                .select(table.fetch(viewType))
                .execute();
    }

    public List<TreeNode> findByParentIsNullAndName(
            @Nullable String name,
            Fetcher<TreeNode> fetcher
    ) {
        return sql
                .createQuery(table)
                .where(table.parent().isNull())
                .where(table.name().eqIf(name))
                .select(table.fetch(fetcher))
                .execute();
    }
}

