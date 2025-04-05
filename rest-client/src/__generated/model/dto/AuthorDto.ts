import type {Gender} from '../enums/';

export type AuthorDto = {
    'AuthorService/COMPLEX_FETCHER': {
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
        readonly books: ReadonlyArray<{
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
        }>;
    }, 
    'AuthorService/DEFAULT_FETCHER': {
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
    }, 
    'AuthorService/SIMPLE_FETCHER': {
        readonly id: number;
        readonly firstName: string;
        readonly lastName: string;
    }
}
