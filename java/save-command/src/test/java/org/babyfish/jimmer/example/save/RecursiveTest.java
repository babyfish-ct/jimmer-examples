package org.babyfish.jimmer.example.save;

import org.babyfish.jimmer.example.save.common.AbstractMutationTest;
import org.babyfish.jimmer.example.save.common.ExecutedStatement;
import org.babyfish.jimmer.example.save.model.TreeNodeDraft;
import org.babyfish.jimmer.example.save.model.TreeNodeProps;
import org.babyfish.jimmer.sql.DissociateAction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;


/**
 * Recommended learning sequence: 6
 *
 *
 * SaveModeTest -> IncompleteObjectTest -> ManyToOneTest ->
 * OneToManyTest -> ManyToManyTest -> [current: RecursiveTest] -> TriggerTest
 */
public class RecursiveTest extends AbstractMutationTest {

    /*
     * Noun explanation
     *
     * Short Association: Association object(s) with only id property.
     * Long Association: Association object(s) with non-id properties.
     */

    @Test
    public void testCreateTree() {
        sql().save(
                /*
                 * `TreeNode` has two key properties: `name` and `parentNode`,
                 * this means `name` and `parentNode` must be specified when `id` is missing.
                 *
                 * One-to-many association is special, parent object can specify the
                 * many-to-one association of its child objects implicitly.
                 * In this demo, Associations named `childNodes` specify `parentNode`
                 * for child objects implicitly so that all child objects do not require
                 * the `parentNode`.
                 *
                 * However, the `parentNode` of ROOT cannot be specified implicitly,
                 * so that it must be specified manually
                 */
                TreeNodeDraft.$.produce(root -> {
                    root.setParentNode(null);
                    root.setName("root");
                    root.addIntoChildNodes(child_1 -> {
                        child_1.setName("child-1");
                        child_1.addIntoChildNodes(child_1_1 -> {
                            child_1_1.setName("child-1-1");
                            child_1_1.setChildNodes(Collections.emptyList());
                        });
                        child_1.addIntoChildNodes(child_1_2 -> {
                            child_1_2.setName("child-1-2");
                            child_1_2.setChildNodes(Collections.emptyList());
                        });
                    });
                    root.addIntoChildNodes(child_2 -> {
                        child_2.setName("child-2");
                        child_2.addIntoChildNodes(child_2_1 -> {
                            child_2_1.setName("child-2-1");
                            child_2_1.setChildNodes(Collections.emptyList());
                        });
                        child_2.addIntoChildNodes(child_2_2 -> {
                            child_2_2.setName("child-2-2");
                            child_2_2.setChildNodes(Collections.emptyList());
                        });
                    });
                })
        );

        assertExecutedStatements(

                // Does the aggregate-root exists?
                //
                // Logically, the `TreeNode` type is decorated by
                // the annotation `@org.babyfish.jimmer.sql.KeyUniqueConstant`,
                // so the database's own upsert capability should be used
                // instead of using a select statement to determine whether
                // the subsequent operation should be inserting or updating.
                //
                // However, the `parentId` of `root` here is null, which
                // does not matter to the database's own upsert capability.
                //
                // To solve this problem, you need to do three things
                //
                // 1.Use postgres and configure the unique constraint to null not distinct
                // (https://www.postgresql.org/about/featurematrix/detail/392/)
                // 2. Configure the dialect `org.babyfish.jimmer.sql.dialect.PostgresDialect`
                // 3. Set the `isNullNotDistinct` parameter of Jimmer's
                // `@KeyUniqueConstant` annotation to true
                ExecutedStatement.of(
                        "select tb_1_.node_id, tb_1_.NAME, tb_1_.parent_id " +
                        "from TREE_NODE tb_1_ " +
                        "where tb_1_.parent_id is null and tb_1_.NAME = ?",
                        "root"
                ),

                // Root does not exist, insert it
                ExecutedStatement.of(
                        "insert into TREE_NODE(NAME, parent_id) values(?, ?)",
                        "root", null
                ),

                // Merge level-1 associated objects
                ExecutedStatement.batchOf(
                        "merge into TREE_NODE(NAME, parent_id) key(NAME, parent_id) values(?, ?)",
                        Arrays.asList("child-1", 100L),
                        Arrays.asList("child-2", 100L)
                ),

                // Merge level-2 associated objects
                ExecutedStatement.batchOf(
                        "merge into TREE_NODE(NAME, parent_id) key(NAME, parent_id) values(?, ?)",
                        Arrays.asList("child-1-1", 101L),
                        Arrays.asList("child-1-2", 101L),
                        Arrays.asList("child-2-1", 102L),
                        Arrays.asList("child-2-2", 102L)
                ),

                /*
                 * From here, dissociates subtrees that are no longer needed.
                 *
                 * These generated SQL statement are highly related to the
                 * global configuration `sqlClient.maxCommandJoinCount`(the default value is 2).
                 * Different configurations will generate completely different SQLs..
                 */

                ExecutedStatement.of(
                        "select tb_1_.node_id " +
                        "from TREE_NODE tb_1_ " +
                        "inner join TREE_NODE tb_2_ on tb_1_.parent_id = tb_2_.node_id " +
                        "inner join TREE_NODE tb_3_ on tb_2_.parent_id = tb_3_.node_id " +
                        "where tb_3_.parent_id = any(?)",
                        Arrays.asList(103L, 104L, 105L, 106L)
                ),

                ExecutedStatement.batchOf(
                        "delete from TREE_NODE tb_1_ " +
                        "where exists(" +
                        "--->select * " +
                        "--->from TREE_NODE tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.parent_id = tb_2_.node_id " +
                        "--->and " +
                        "--->--->parent_id = ? " +
                        "--->and " +
                        "--->--->not (node_id = any(?))" +
                        ")",
                        Arrays.asList(103L, Collections.<Long>emptyList()),
                        Arrays.asList(104L, Collections.<Long>emptyList()),
                        Arrays.asList(105L, Collections.<Long>emptyList()),
                        Arrays.asList(106L, Collections.<Long>emptyList())
                ),

                ExecutedStatement.batchOf(
                        "delete from TREE_NODE " +
                        "where parent_id = ? and not (node_id = any(?))",
                        Arrays.asList(103L, Collections.<Long>emptyList()),
                        Arrays.asList(104L, Collections.<Long>emptyList()),
                        Arrays.asList(105L, Collections.<Long>emptyList()),
                        Arrays.asList(106L, Collections.<Long>emptyList())
                ),

                ExecutedStatement.of(
                        "select tb_1_.node_id " +
                                "from TREE_NODE tb_1_ " +
                                "inner join TREE_NODE tb_2_ on tb_1_.parent_id = tb_2_.node_id " +
                                "inner join TREE_NODE tb_3_ on tb_2_.parent_id = tb_3_.node_id " +
                                "where " +
                                "--->tb_3_.parent_id = any(?) " +
                                "and " +
                                "--->(tb_3_.parent_id, tb_2_.parent_id) not in ((?, ?), (?, ?), (?, ?), (?, ?))",
                        Arrays.asList(101L, 102L), 101L, 103L, 101L, 104L, 102L, 105L, 102L, 106L
                ),

                ExecutedStatement.batchOf(
                        "delete from TREE_NODE tb_1_ " +
                                "where exists(" +
                                "--->select * " +
                                "--->from TREE_NODE tb_2_ " +
                                "--->where " +
                                "--->--->tb_1_.parent_id = tb_2_.node_id " +
                                "--->and " +
                                "--->--->parent_id = ? " +
                                "--->and " +
                                "--->--->not (node_id = any(?))" +
                                ")",
                        Arrays.asList(101L, Arrays.asList(103L, 104L)),
                        Arrays.asList(102L, Arrays.asList(105L, 106L))
                ),

                ExecutedStatement.batchOf(
                        "delete from TREE_NODE " +
                        "where parent_id = ? and not (node_id = any(?))",
                        Arrays.asList(101L, Arrays.asList(103L, 104L)),
                        Arrays.asList(102L, Arrays.asList(105L, 106L))
                )
        );
    }

    @Test
    public void testDeleteSubTrees() {
        jdbc(
                "insert into tree_node(node_id, name, parent_id) values" +
                        "(1, 'root', null)," +
                        "    (2, 'child-1', 1)," +
                        "        (3, 'child-1-1', 2)," +
                        "        (4, 'child-1-2', 2)," +
                        "    (5, 'child-2', 1)," +
                        "        (6, 'child-2-1', 5)," +
                        "        (7, 'child-2-2', 5)"
        );

        sql().save(
                // Please view the comment of `testCreateTree` to understand
                // why `parentNode` is a key property of `TreeNode`
                // but only the root node needs it.
                TreeNodeDraft.$.produce(root -> {
                    root.setParentNode(null);
                    root.setName("root");
                    root.addIntoChildNodes(child_1 -> {
                        child_1.setName("child-1");
                        child_1.addIntoChildNodes(child_1_1 -> {
                            child_1_1.setName("child-1-1");
                            child_1_1.setChildNodes(Collections.emptyList());
                        });
                        // The `child-1-2` is not specified here,
                        // so the subtree `child-1-2` in database
                        // will be detached
                    });
                    // The `child-2` is not specified here,
                    // so the subtree
                    // `-+-child-2`
                    // ` "`
                    // ` +----child-2-1`
                    // ` "`
                    // `-"----child-2-2`
                    // in database will be detached
                })
        );

        assertExecutedStatements(

                // Does the aggregate-root exists?
                //
                // Logically, the `TreeNode` type is decorated by
                // the annotation `@org.babyfish.jimmer.sql.KeyUniqueConstant`,
                // so the database's own upsert capability should be used
                // instead of using a select statement to determine whether
                // the subsequent operation should be inserting or updating.
                //
                // However, the `parentId` of `root` here is null, which
                // does not matter to the database's own upsert capability.
                //
                // To solve this problem, you need to do three things
                //
                // 1.Use postgres and configure the unique constraint to null not distinct
                // (https://www.postgresql.org/about/featurematrix/detail/392/)
                // 2. Configure the dialect `org.babyfish.jimmer.sql.dialect.PostgresDialect`
                // 3. Set the `isNullNotDistinct` parameter of Jimmer's
                // `@KeyUniqueConstant` annotation to true
                ExecutedStatement.of(
                        "select tb_1_.node_id, tb_1_.NAME, tb_1_.parent_id " +
                        "from TREE_NODE tb_1_ " +
                        "where tb_1_.parent_id is null and tb_1_.NAME = ?",
                        "root"
                ),

                // The aggregate-root exists, do nothing

                // Merge level-1 associated objects
                ExecutedStatement.of(
                        "merge into TREE_NODE(NAME, parent_id) " +
                                "key(NAME, parent_id) " +
                                "values(?, ?)",
                        "child-1", 1L
                ),

                // Merge level-2 associated objects
                ExecutedStatement.of(
                        "merge into TREE_NODE(NAME, parent_id) " +
                        "key(NAME, parent_id) " +
                        "values(?, ?)",
                        "child-1-1", 2L
                ),

                /*
                 * From here, dissociates subtrees that are no longer needed.
                 *
                 * These generated SQL statement are highly related to the
                 * global configuration `sqlClient.maxCommandJoinCount`(the default value is 2).
                 * Different configurations will generate completely different SQLs..
                 */

                ExecutedStatement.of(
                        "select tb_1_.node_id " +
                                "from TREE_NODE tb_1_ " +
                                "inner join TREE_NODE tb_2_ on tb_1_.parent_id = tb_2_.node_id " +
                                "inner join TREE_NODE tb_3_ on tb_2_.parent_id = tb_3_.node_id " +
                                "where tb_3_.parent_id = ?",
        3L
            ),

        ExecutedStatement.of(
                "delete from TREE_NODE tb_1_ " +
                        "where exists(" +
                        "--->select * " +
                        "--->from TREE_NODE tb_2_ " +
                        "--->where " +
                        "--->--->tb_1_.parent_id = tb_2_.node_id " +
                        "--->and " +
                        "--->--->parent_id = ? " +
                        "--->and " +
                        "--->--->not (node_id = any(?))" +
                        ")",
        3L, Collections.<Long>emptyList()
            ),
        ExecutedStatement.of(
                "delete from TREE_NODE " +
                        "where parent_id = ? and not (node_id = any(?))",
                3L, Collections.<Long>emptyList()
        ),

                ExecutedStatement.of(
                        "select tb_1_.node_id " +
                                "from TREE_NODE tb_1_ " +
                        "inner join TREE_NODE tb_2_ on tb_1_.parent_id = tb_2_.node_id " +
                                "inner join TREE_NODE tb_3_ on tb_2_.parent_id = tb_3_.node_id " +
                                "where tb_3_.parent_id = ? and tb_2_.parent_id <> ?",
                        2L, 3L
                ),

                ExecutedStatement.of(
                        "delete from TREE_NODE tb_1_ " +
                                "where exists(" +
                                "--->select * " +
                                "--->from TREE_NODE tb_2_ " +
                                "--->where " +
                                "--->--->tb_1_.parent_id = tb_2_.node_id " +
                                "--->and " +
                                "--->--->parent_id = ? " +
                                "--->and " +
                                "--->--->not (node_id = any(?))" +
                                ")",
                        2L, Arrays.asList(3L)
                ),

                ExecutedStatement.of(
                        "delete from TREE_NODE where parent_id = ? and not (node_id = any(?))",
                        2L, Arrays.asList(3L)
                ),

                ExecutedStatement.of(
                        "select tb_1_.node_id " +
                                "from TREE_NODE tb_1_ " +
                                "inner join TREE_NODE tb_2_ on tb_1_.parent_id = tb_2_.node_id " +
                                "inner join TREE_NODE tb_3_ on tb_2_.parent_id = tb_3_.node_id " +
                                "where tb_3_.parent_id = ? and tb_2_.parent_id <> ?",
                        1L, 2L
                ),

                ExecutedStatement.of(
                        "delete from TREE_NODE tb_1_ " +
                                "where exists(" +
                                "--->select * " +
                                "--->from TREE_NODE tb_2_ " +
                                "--->where " +
                                "--->--->tb_1_.parent_id = tb_2_.node_id " +
                                "--->and " +
                                "--->--->parent_id = ? " +
                                "--->and " +
                                "--->--->not (node_id = any(?))" +
                                ")",
                        1L, Collections.singletonList(2L)
                ),

                ExecutedStatement.of(
                        "delete from TREE_NODE " +
                                "where " +
                                "--->parent_id = ? " +
                                "and " +
                                "--->not (node_id = any(?))",
                        1L, Collections.singletonList(2L)
                )
        );
    }
}