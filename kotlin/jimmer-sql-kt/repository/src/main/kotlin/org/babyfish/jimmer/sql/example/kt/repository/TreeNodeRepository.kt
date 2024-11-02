package org.babyfish.jimmer.sql.example.kt.repository

import org.babyfish.jimmer.View
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.example.kt.model.TreeNode
import org.babyfish.jimmer.sql.example.kt.model.name
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.babyfish.jimmer.sql.kt.ast.expression.`like?`
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
class TreeNodeRepository(
    sql: KSqlClient
) : AbstractKotlinRepository<TreeNode, Long>(sql) {

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
    fun <V: View<TreeNode>> findByNameLikeIgnoreCase(
        name: String?,
        viewType: KClass<V>
    ): List<V> =
        executeQuery {
            where(table.name `like?` name)
            select(table.fetch(viewType))
        }

    fun findByParentIsNullAndName(
        name: String?,
        fetcher: Fetcher<TreeNode>?
    ): List<TreeNode> =
        executeQuery {
            where(table.name.isNull())
            where(table.name `eq?` name)
            select(table.fetch(fetcher))
        }
}

