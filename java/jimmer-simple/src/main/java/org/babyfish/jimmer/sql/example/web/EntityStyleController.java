package org.babyfish.jimmer.sql.example.web;

import org.babyfish.jimmer.client.FetchBy;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.*;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entity")
@Transactional
public class EntityStyleController implements Tables, Fetchers {

    private final JSqlClient sqlClient;

    public EntityStyleController(JSqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    @GetMapping("/books/simple")
    public List<@FetchBy("SIMPLE_BOOK") Book> findSimpleBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().ilikeIf(name))
                .select(table.fetch(SIMPLE_BOOK))
                .execute();
    }

    @GetMapping("/books/complex")
    public List<@FetchBy("COMPLEX_BOOK") Book> findComplexBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().ilikeIf(name))
                .select(table.fetch(COMPLEX_BOOK))
                .execute();
    }

    @GetMapping("/rootTreeNodes/simple")
    public List<@FetchBy("SIMPLE_TREE_NODE") TreeNode> findSimpleTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(SIMPLE_TREE_NODE))
                .execute();
    }

    @GetMapping("/rootTreeNodes/recursive")
    public List<@FetchBy("RECURSIVE_TREE_NODE") TreeNode> findRecursiveTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(RECURSIVE_TREE_NODE))
                .execute();
    }

    private static final Fetcher<Book> SIMPLE_BOOK =
            BOOK_FETCHER
                    .name();

    private static final Fetcher<Book> COMPLEX_BOOK =
            BOOK_FETCHER
                    .allScalarFields()
                    .store(
                            BOOK_STORE_FETCHER
                                    .allScalarFields()
                    )
                    .authors(
                            AUTHOR_FETCHER
                                    .allScalarFields()
                    );

    private static final Fetcher<TreeNode> SIMPLE_TREE_NODE =
            TREE_NODE_FETCHER
                    .name();

    private static final Fetcher<TreeNode> RECURSIVE_TREE_NODE =
            TREE_NODE_FETCHER
                    .allScalarFields()
                    .recursiveChildNodes();
}
