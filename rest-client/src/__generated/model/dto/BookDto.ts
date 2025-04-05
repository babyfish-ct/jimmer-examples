import type {Gender} from '../enums/';

export type BookDto = {
    /**
     * Complex Book DTO that contains
     * <ul>
     *     <li>All scalar properties except `tenant` of current `Book` entity</li>
     *     <li>`id`, `name` and the calculation property `avgPrice` of the associated `BookStore` object provided by many-to-one association `store`</li>
     *     <li>all scalar properties of the associated `Author` objects provided by many-to-many association `authors`</li>
     * </ul>
     */
    'BookService/COMPLEX_FETCHER': {
        /**
         * The surrogate id of the current object,
         * auto-incrementing,
         * without specific business meaning
         */
        readonly id: number;
        /**
         * The time when the object was created.
         * 
         * <p>In this example, this property is not
         * explicitly modified by business code,
         * but is automatically modified by {@code DraftInterceptor}</p>
         */
        readonly createdTime: string;
        /**
         * The time when the object was last modified
         * 
         * <p>In this example, this property is not
         * explicitly modified by business code,
         * but is automatically modified by {@code DraftInterceptor}</p>
         */
        readonly modifiedTime: string;
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
         * The bookstore to which the current book belongs,
         * representing a many-to-one association
         */
        readonly store?: {
            readonly id: number;
            /**
             * The time when the object was created.
             * 
             * <p>In this example, this property is not
             * explicitly modified by business code,
             * but is automatically modified by {@code DraftInterceptor}</p>
             */
            readonly createdTime: string;
            /**
             * The time when the object was last modified
             * 
             * <p>In this example, this property is not
             * explicitly modified by business code,
             * but is automatically modified by {@code DraftInterceptor}</p>
             */
            readonly modifiedTime: string;
            readonly name: string;
            readonly website?: string | undefined;
            /**
             * This is a calculated scalar property,
             * the average price of all books in the current bookstore
             */
            readonly avgPrice: number;
        } | undefined;
        /**
         * All authors who participated in writing
         * the current book,
         * representing a many-to-many association
         */
        readonly authors: ReadonlyArray<{
            readonly id: number;
            /**
             * The time when the object was created.
             * 
             * <p>In this example, this property is not
             * explicitly modified by business code,
             * but is automatically modified by {@code DraftInterceptor}</p>
             */
            readonly createdTime: string;
            /**
             * The time when the object was last modified
             * 
             * <p>In this example, this property is not
             * explicitly modified by business code,
             * but is automatically modified by {@code DraftInterceptor}</p>
             */
            readonly modifiedTime: string;
            readonly firstName: string;
            readonly lastName: string;
            readonly gender: Gender;
        }>;
    }, 
    /**
     * Default Book DTO that contains
     * <ul>
     *     <li>All scalar properties except `tenant` of current `Book` entity</li>
     *     <li>`id` and `name` of the associated `BookStore` object provided by many-to-one association `store`</li>
     *     <li>`id`, `firstName` and `lastName` of the associated `Author` objects provided by many-to-many association `authors`</li>
     * </ul>
     */
    'BookService/DEFAULT_FETCHER': {
        /**
         * The surrogate id of the current object,
         * auto-incrementing,
         * without specific business meaning
         */
        readonly id: number;
        /**
         * The time when the object was created.
         * 
         * <p>In this example, this property is not
         * explicitly modified by business code,
         * but is automatically modified by {@code DraftInterceptor}</p>
         */
        readonly createdTime: string;
        /**
         * The time when the object was last modified
         * 
         * <p>In this example, this property is not
         * explicitly modified by business code,
         * but is automatically modified by {@code DraftInterceptor}</p>
         */
        readonly modifiedTime: string;
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
         * The bookstore to which the current book belongs,
         * representing a many-to-one association
         */
        readonly store?: {
            readonly id: number;
            readonly name: string;
        } | undefined;
        /**
         * All authors who participated in writing
         * the current book,
         * representing a many-to-many association
         */
        readonly authors: ReadonlyArray<{
            readonly id: number;
            readonly firstName: string;
            readonly lastName: string;
        }>;
    }, 
    /**
     * Simple Book DTO that only contains `id` and `name`
     */
    'BookService/SIMPLE_FETCHER': {
        /**
         * The surrogate id of the current object,
         * auto-incrementing,
         * without specific business meaning
         */
        readonly id: number;
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
    }
}
