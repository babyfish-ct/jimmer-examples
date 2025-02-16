package org.babyfish.jimmer.sql.example.kt.web

import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.sql.example.kt.model.*
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entity")
@Transactional
class EntityStyleController(
    private val sqlClient: KSqlClient
) {
    @GetMapping("/books/simple")
    fun findSimpleBooks(
        @RequestParam(required = false) name: String?
    ): List<@FetchBy("SIMPLE_BOOK") Book> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(SIMPLE_BOOK))
        }

    @GetMapping("/books/complex")
    fun findComplexBooks(
        @RequestParam(required = false) name: String?
    ): List<@FetchBy("COMPLEX_BOOK") Book> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(COMPLEX_BOOK))
        }

    @GetMapping("/rootTreeNodes/simple")
    fun findSimpleTreeNodes(
    ): List<@FetchBy("SIMPLE_TREE_NODE") TreeNode> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(SIMPLE_TREE_NODE))
        }

    @GetMapping("/rootTreeNodes/recursive")
    fun findRecursiveTreeNodes(
    ): List<@FetchBy("RECURSIVE_TREE_NODE") TreeNode> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(RECURSIVE_TREE_NODE))
        }

    companion object {

        private val SIMPLE_BOOK =
            newFetcher(Book::class).by {
                name()
            }

        private val COMPLEX_BOOK =
            newFetcher(Book::class).by {
                allScalarFields()
                store {
                    allScalarFields()
                }
                authors {
                    allScalarFields()
                }
            }

        private val SIMPLE_TREE_NODE =
            newFetcher(TreeNode::class).by {
                name()
            }

        private val RECURSIVE_TREE_NODE =
            newFetcher(TreeNode::class).by {
                allScalarFields()
                `childNodes*`()
            }
    }
}