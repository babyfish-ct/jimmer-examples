import type {Gender} from '../enums/';

export type BookStoreDto = {
    /**
     * Default BookStore DTO that contains all scalar properties
     */
    'BookStoreService/DEFAULT_FETCHER': {
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
    }, 
    /**
     * Simple BookStore DTO that only contains `id` and `name`
     */
    'BookStoreService/SIMPLE_FETCHER': {
        readonly id: number;
        readonly name: string;
    }, 
    /**
     * BookStore DTO contains
     * <ul>
     *     <li>all scalar properties</li>
     *     <li>The calculated-property `avgPrice`</li>
     *     <li>
     *         Associated `Book` objects provided by many-to-many property `books`,
     *         each `Book` object contains deeper `Author` objects.
     *     </li>
     * </ul>
     */
    'BookStoreService/WITH_ALL_BOOKS_FETCHER': {
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
        /**
         * All books belonging to the current bookstore,
         * representing a one-to-many association
         */
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
        }>;
    }, 
    /**
     * BookStore DTO contains
     * <ul>
     *     <li>all scalar properties</li>
     *     <li>The calculated-property `avgPrice`</li>
     *     <li>
     *         Associated `Book` objects provided by calculated association property `newestBooks`,
     *         each `Book` object contains deeper `Author` objects.
     *     </li>
     * </ul>
     */
    'BookStoreService/WITH_NEWEST_BOOKS_FETCHER': {
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
        /**
         * This is a calculated associated property.
         * 
         * <p>Since books have an {@link Book#edition()}
         * properties, the books relationship may contain
         * books with the same name.</p>
         * 
         * This collection only selects books with the highest
         * edition to avoid naming conflicts,
         * therefore this collection is always a subset of the
         * {@link #books()} collection
         */
        readonly newestBooks: ReadonlyArray<{
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
        }>;
    }
}
