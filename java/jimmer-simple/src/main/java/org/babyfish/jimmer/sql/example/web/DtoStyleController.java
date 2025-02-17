package org.babyfish.jimmer.sql.example.web;

import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.*;
import org.babyfish.jimmer.sql.example.model.dto.ComplexBookView;
import org.babyfish.jimmer.sql.example.model.dto.RecursiveTreeNodeView;
import org.babyfish.jimmer.sql.example.model.dto.SimpleBookView;
import org.babyfish.jimmer.sql.example.model.dto.SimpleTreeNodeView;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dto")
@Transactional
public class DtoStyleController implements Tables {

    private final JSqlClient sqlClient;

    public DtoStyleController(JSqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    /**
     * Find simple book objects.
     *
     * <p>If this parameter is specified, only books with the specified name will be queried.</p>
     *
     * @param name Optional query parameter,
     *      {@code null} or {@code ""} will be ignored
     * @return A list of Book objects, each of which
     *      has only {@code id} and {@code name} properties
     */
    @GetMapping("/books/simple")
    public List<SimpleBookView> findSimpleBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().eqIf(name))
                .select(table.fetch(SimpleBookView.class))
                .execute();
    }

    /**
     * Find complex book objects.
     *
     * <p>If this parameter is specified, only books with the specified name will be queried.</p>
     *
     * @param name Optional query parameter,
     *      {@code null} or {@code ""} will be ignored
     * @return A list of Book objects, each of which
     *      has all scalar properties, associated
     *      {@link BookStore} and associated
     *      {@link Author} objects.
     */
    @GetMapping("/books/complex")
    public List<ComplexBookView> findComplexBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().eqIf(name))
                .select(table.fetch(ComplexBookView.class))
                .execute();
    }

    /**
     * Query root tree nodes, that is, nodes whose parentId is null.
     *
     * @return All list of root tree nodes, each of which
     *         has only {@code id} and {@code name} properties
     */
    @GetMapping("/rootTreeNodes/simple")
    public List<SimpleTreeNodeView> findSimpleTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(SimpleTreeNodeView.class))
                .execute();
    }

    /**
     * Query root tree nodes, that is, nodes whose parentId is null.
     *
     * @return All list of root tree nodes, each of which
     *         has all scalar properties, associated child objects.
     *         If a child node has deeper child nodes,
     *         it will be recursively associated until
     *         there are no deeper child nodes.
     */
    @GetMapping("/rootTreeNodes/recursive")
    public List<RecursiveTreeNodeView> findRecursiveTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(RecursiveTreeNodeView.class))
                .execute();
    }
}
