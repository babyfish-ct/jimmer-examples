export interface BookSpecification {
    /**
     * The name of current Book.
     * 
     * <p>This property forms a unique constraint together with
     * the {@code edition} property, which is {@code @Key} of Jimmer</p>
     */
    readonly name?: string | undefined;
    readonly minPrice?: number | undefined;
    readonly maxPrice?: number | undefined;
    readonly storeName?: string | undefined;
    readonly authorName?: string | undefined;
}
