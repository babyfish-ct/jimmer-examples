export interface BookInput {
    /**
     * The surrogate id of the current object,
     * auto-incrementing,
     * without specific business meaning
     */
    readonly id?: number | undefined;
    /**
     * The name of current Book.
     * 
     * <p>This property forms a unique constraint together with
     * the {@code edition} property, which is {@code @Key} of Jimmer</p>
     */
    readonly name: string;
    /**
     * The edition of current Book.
     * 
     * <p>This property forms a unique constraint together with
     * the {@code name} property, which is {@code @Key} of Jimmer</p>
     */
    readonly edition: number;
    readonly price: number;
    /**
     * You can also write `storeId` because the @IdView property `Book.storeId` has already been defined.
     * 
     * However, this is better way to not assume that `@IdView` property must be present.
     */
    readonly storeId?: number | undefined;
    /**
     * You can also write `authorIds` because the @IdView property `Book.authorIds` has already been defined.
     * 
     * However, this is better way to not assume that `@IdView` property must be present.
     */
    readonly authorIds: ReadonlyArray<number>;
}
