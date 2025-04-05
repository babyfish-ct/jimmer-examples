export interface CompositeBookStoreInput_TargetOf_books {
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
     * All authors who participated in writing
     * the current book,
     * representing a many-to-many association
     */
    readonly authorIds: ReadonlyArray<number>;
}
