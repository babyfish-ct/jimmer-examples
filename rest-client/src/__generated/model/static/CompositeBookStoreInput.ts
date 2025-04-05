import type {CompositeBookStoreInput_TargetOf_books} from './';

export interface CompositeBookStoreInput {
    readonly name: string;
    readonly website?: string | undefined;
    /**
     * All books belonging to the current bookstore,
     * representing a one-to-many association
     */
    readonly books: ReadonlyArray<CompositeBookStoreInput_TargetOf_books>;
}
