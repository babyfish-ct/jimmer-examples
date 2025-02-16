package org.babyfish.jimmer.sql.example.web;

import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.example.model.BookTable;
import org.babyfish.jimmer.sql.example.model.Tables;
import org.babyfish.jimmer.sql.example.model.TreeNodeTable;
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

    @GetMapping("/books/simple")
    public List<SimpleBookView> findSimpleBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().ilikeIf(name))
                .select(table.fetch(SimpleBookView.class))
                .execute();
    }

    @GetMapping("/books/complex")
    public List<ComplexBookView> findComplexBooks(
            @Nullable @RequestParam(required = false) String name
    ) {
        BookTable table = BOOK_TABLE;
        return sqlClient.createQuery(table)
                .where(table.name().ilikeIf(name))
                .select(table.fetch(ComplexBookView.class))
                .execute();
    }

    @GetMapping("/rootTreeNodes/simple")
    public List<SimpleTreeNodeView> findSimpleTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(SimpleTreeNodeView.class))
                .execute();
    }

    @GetMapping("/rootTreeNodes/recursive")
    public List<RecursiveTreeNodeView> findRecursiveTreeNodes() {
        TreeNodeTable table = TREE_NODE_TABLE;
        return sqlClient.createQuery(table)
                .where(table.parentId().isNull())
                .select(table.fetch(RecursiveTreeNodeView.class))
                .execute();
    }
}
