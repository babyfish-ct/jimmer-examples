package org.babyfish.jimmer.sql.example.kt.web

import org.babyfish.jimmer.sql.example.kt.model.Book
import org.babyfish.jimmer.sql.example.kt.model.TreeNode
import org.babyfish.jimmer.sql.example.kt.model.dto.ComplexBookView
import org.babyfish.jimmer.sql.example.kt.model.dto.RecursiveTreeNodeView
import org.babyfish.jimmer.sql.example.kt.model.dto.SimpleBookView
import org.babyfish.jimmer.sql.example.kt.model.dto.SimpleTreeNodeView
import org.babyfish.jimmer.sql.example.kt.model.name
import org.babyfish.jimmer.sql.example.kt.model.parentId
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

    @GetMapping("/books/simple")
    fun findSimpleBooks(
        @RequestParam(required = false) name: String?
    ): List<SimpleBookView> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(SimpleBookView::class))
        }

    @GetMapping("/books/complex")
    fun findComplexBooks(
        @RequestParam(required = false) name: String?
    ): List<ComplexBookView> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(ComplexBookView::class))
        }

    @GetMapping("/rootTreeNodes/simple")
    fun findSimpleTreeNodes(
    ): List<SimpleTreeNodeView> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(SimpleTreeNodeView::class))
        }

    @GetMapping("/rootTreeNodes/recursive")
    fun findRecursiveTreeNodes(
    ): List<RecursiveTreeNodeView> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(RecursiveTreeNodeView::class))
        }
}