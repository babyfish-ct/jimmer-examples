export interface RecursiveTreeInput {
    /**
     * The name of current TreeNode.
     * 
     * <p>This property forms a unique constraint together with
     * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
     */
    readonly name: string;
    readonly childNodes?: ReadonlyArray<RecursiveTreeInput> | undefined;
}
