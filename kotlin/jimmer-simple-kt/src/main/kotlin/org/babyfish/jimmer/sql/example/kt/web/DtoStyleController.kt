package org.babyfish.jimmer.sql.example.kt.web

import org.babyfish.jimmer.sql.example.kt.model.*
import org.babyfish.jimmer.sql.example.kt.model.dto.ComplexBookView
import org.babyfish.jimmer.sql.example.kt.model.dto.RecursiveTreeNodeView
import org.babyfish.jimmer.sql.example.kt.model.dto.SimpleBookView
import org.babyfish.jimmer.sql.example.kt.model.dto.SimpleTreeNodeView
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dto")
@Transactional
class DtoStyleController(
    private val sqlClient: KSqlClient
) {

    /**
     * Find simple book objects.
     *
     * If this parameter is specified, only books with the specified name will be queried.
     *
     * @param name Optional query parameter,
     * `null` and `""` will be ignored
     *
     * @return A list of Book objects, each of which
     * has only `name` and `edition` properties
     */
    @GetMapping("/books/simple")
    fun findSimpleBooks(
        @RequestParam(required = false) name: String?
    ): List<SimpleBookView> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(SimpleBookView::class))
        }

    /**
     * Find complex book objects.
     *
     * If this parameter is specified, only books with the specified name will be queried.
     *
     * @param name Optional query parameter,
     * `null` and `""` will be ignored
     *
     * @return A list of Book objects, each of which
     * has all scalar properties, associated
     * [BookStore] and associated
     * [Author] objects.
     */
    @GetMapping("/books/complex")
    fun findComplexBooks(
        @RequestParam(required = false) name: String?
    ): List<ComplexBookView> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(ComplexBookView::class))
        }

    /**
     * Query root tree nodes, that is, nodes whose parentId is null.
     *
     * @return All list of root tree nodes, each of which
     * has only `id` and `name` properties
     */
    @GetMapping("/rootTreeNodes/simple")
    fun findSimpleTreeNodes(
    ): List<SimpleTreeNodeView> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(SimpleTreeNodeView::class))
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
    fun findRecursiveTreeNodes(
    ): List<RecursiveTreeNodeView> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(RecursiveTreeNodeView::class))
        }
}