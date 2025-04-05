import type {Gender} from '../enums/';

export interface AuthorSpecification {
    readonly firstName?: string | undefined;
    readonly lastName?: string | undefined;
    readonly gender?: Gender | undefined;
    /**
     * The time when the object was created.
     * 
     * <p>In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by {@code DraftInterceptor}</p>
     */
    readonly minCreatedTime?: string | undefined;
    /**
     * The time when the object was created.
     * 
     * <p>In this example, this property is not
     * explicitly modified by business code,
     * but is automatically modified by {@code DraftInterceptor}</p>
     */
    readonly maxCreatedTime?: string | undefined;
}
