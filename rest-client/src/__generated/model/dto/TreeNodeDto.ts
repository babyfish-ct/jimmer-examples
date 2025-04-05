export type TreeNodeDto = {
    'TreeService/DETAIL_FETCHER': {
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
         * The name of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly name: string;
        /**
         * The parent of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #name()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly parent?: TreeNodeDto['TreeService/DETAIL_FETCHER@parent'] | undefined;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/DETAIL_FETCHER@childNodes']> | undefined;
    }, 
    'TreeService/RECURSIVE_FETCHER': {
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
         * The name of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly name: string;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/RECURSIVE_FETCHER']> | undefined;
    }, 
    'TreeService/DETAIL_FETCHER@parent': {
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
         * The name of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly name: string;
        /**
         * The parent of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #name()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly parent?: TreeNodeDto['TreeService/DETAIL_FETCHER@parent'] | undefined;
    }, 
    'TreeService/DETAIL_FETCHER@childNodes': {
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
         * The name of current TreeNode.
         * 
         * <p>This property forms a unique constraint together with
         * the {@link #parent()} property, which is {@code @Key} of Jimmer</p>
         */
        readonly name: string;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/DETAIL_FETCHER@childNodes']> | undefined;
    }
}
