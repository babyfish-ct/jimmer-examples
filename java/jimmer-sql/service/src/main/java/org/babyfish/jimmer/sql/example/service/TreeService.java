package org.babyfish.jimmer.sql.example.service;

import org.babyfish.jimmer.client.FetchBy;
import org.babyfish.jimmer.sql.example.model.*;
import org.babyfish.jimmer.sql.example.repository.TreeNodeRepository;
import org.babyfish.jimmer.sql.example.service.dto.FlatTreeNodeView;
import org.babyfish.jimmer.sql.example.service.dto.RecursiveTreeInput;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.babyfish.jimmer.sql.exception.SaveException;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Why add spring web annotations to the service class?
 *
 * The success and popularity of rich client technologies represented by React, Vue and Angular
 * have greatly reduced the significance of the Controller layer on the spring server side.
 *
 * Moreover, over-bloated code structures are not conducive to demonstrating the capabilities
 * of the framework with small examples. Therefore, this example project no longer adheres to
 * dogmatism and directly adds spring web annotations to the service class.
 */
@RestController
@Transactional
@RequestMapping("/tree")
public class TreeService implements Fetchers {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeService.class);

    private final TreeNodeRepository treeNodeRepository;

    public TreeService(TreeNodeRepository treeNodeRepository) {
        this.treeNodeRepository = treeNodeRepository;
    }

    @GetMapping("/flatNodes")
    public List<FlatTreeNodeView> flatNodes(
            @RequestParam(required = false) String name
    ) {
        return treeNodeRepository.findByNameLikeIgnoreCase(
                name,
                FlatTreeNodeView.class
        );
    }

    @GetMapping("/roots/recursive")
    public List<@FetchBy("RECURSIVE_FETCHER") TreeNode> findRootTrees(
            @RequestParam(required = false) String rootName
    ) {
        return treeNodeRepository.findByParentIsNullAndName(
                rootName,
                RECURSIVE_FETCHER
        );
    }

    @GetMapping("/node/{id}")
    @Nullable
    public @FetchBy("DETAIL_FETCHER") TreeNode findNodeById(
            @PathVariable long id
    ) {
        return treeNodeRepository.findById(id, DETAIL_FETCHER);
    }

    @PutMapping("/root/recursive")
    public TreeNode saveTree(@RequestBody RecursiveTreeInput input) throws SaveException {
        TreeNode rootNode = Immutables.createTreeNode(

                input.toEntity(),

                /*
                 * `TreeNode` has two key properties: `name` and `parent`,
                 * this means `name` and `parent` must be specified when `id` is missing.
                 *
                 * One-to-many association is special, parent object can specify the
                 * many-to-one association of its child objects implicitly.
                 * In this demo, Associations named `childNodes` specify `parent`
                 * for child objects implicitly so that all child objects do not require
                 * the `parent`.
                 *
                 * However, the `parent` of ROOT cannot be specified implicitly,
                 * so that it must be specified manually
                 */
                draft -> draft.setParent(null)
        );
        return treeNodeRepository.save(rootNode).getModifiedEntity();
    }

    @DeleteMapping("/{id}")
    public void deleteTree(@PathVariable long id) {
        treeNodeRepository.deleteById(id);
    }

    private static final Fetcher<TreeNode> RECURSIVE_FETCHER =
            TREE_NODE_FETCHER
                    .allScalarFields()
                    .recursiveChildNodes();

    private static final Fetcher<TreeNode> DETAIL_FETCHER =
            TREE_NODE_FETCHER
                    .allScalarFields()
                    .recursiveParent()
                    .recursiveChildNodes();
}
