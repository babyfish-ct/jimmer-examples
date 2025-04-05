export interface FlatTreeNodeView {
    readonly id: number;
    /**
     * The name of current TreeNode.
     * 
     * <p>This property forms a unique constraint together with
     * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
     */
    readonly name: string;
    readonly parentId?: number | undefined;
    /**
     * The name of current TreeNode.
     * 
     * <p>This property forms a unique constraint together with
     * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
     */
    readonly parentName?: string | undefined;
    readonly grandParentId?: number | undefined;
    /**
     * The name of current TreeNode.
     * 
     * <p>This property forms a unique constraint together with
     * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
     */
    readonly grandParentName?: string | undefined;
}
