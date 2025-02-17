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
    ): List<@FetchBy("SIMPLE_BOOK") Book> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(SIMPLE_BOOK))
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
    ): List<@FetchBy("COMPLEX_BOOK") Book> =
        sqlClient.executeQuery(Book::class) {
            where(table.name `eq?` name)
            select(table.fetch(COMPLEX_BOOK))
        }

    /**
     * Query root tree nodes, that is, nodes whose parentId is null.
     *
     * @return All list of root tree nodes, each of which
     * has only `id` and `name` properties
     */
    @GetMapping("/rootTreeNodes/simple")
    fun findSimpleTreeNodes(
    ): List<@FetchBy("SIMPLE_TREE_NODE") TreeNode> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(SIMPLE_TREE_NODE))
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
    ): List<@FetchBy("RECURSIVE_TREE_NODE") TreeNode> =
        sqlClient.executeQuery(TreeNode::class) {
            where(table.parentId.isNull())
            select(table.fetch(RECURSIVE_TREE_NODE))
        }

    companion object {

        /**
         * Simple Book DTO defined by ObjectFetcher
         */
        private val SIMPLE_BOOK =
            newFetcher(Book::class).by {
                name()
            }

        /**
         * Complex Book DTO defined by ObjectFetcher
         */
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

        /**
         * Simple TreeNode DTO defined by ObjectFetcher
         */
        private val SIMPLE_TREE_NODE =
            newFetcher(TreeNode::class).by {
                name()
            }

        /**
         * Recursive TreeNode DTO defined by ObjectFetcher
         */
        private val RECURSIVE_TREE_NODE =
            newFetcher(TreeNode::class).by {
                allScalarFields()
                `childNodes*`()
            }
    }
}