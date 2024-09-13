import type {SaveErrorCode} from './model/enums/';
import type {ExportedSavePath} from './model/static/';

export type AllErrors = {
        family: 'SAVE_COMMAND', 
        code: 'READONLY_MIDDLE_TABLE', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NULL_TARGET', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'CANNOT_DISSOCIATE_TARGETS', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NO_ID_GENERATOR', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'ILLEGAL_ID_GENERATOR', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'ILLEGAL_GENERATED_ID', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NO_KEY_PROP', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NO_VERSION', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'OPTIMISTIC_LOCK_ERROR', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NEITHER_ID_NOR_KEY', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'REVERSED_REMOTE_ASSOCIATION', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'LONG_REMOTE_ASSOCIATION', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'FAILED_REMOTE_VALIDATION', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'UNSTRUCTURED_ASSOCIATION', 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'TARGET_IS_NOT_TRANSFERABLE', 
        saveErrorCode: SaveErrorCode, 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'INCOMPLETE_PROPERTY', 
        saveErrorCode: SaveErrorCode, 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'NOT_UNIQUE', 
        saveErrorCode: SaveErrorCode, 
        exportedPath: ExportedSavePath
    } | {
        family: 'SAVE_COMMAND', 
        code: 'ILLEGAL_TARGET_ID', 
        exportedPath: ExportedSavePath
    };
export type ApiErrors = {
    'authorService': {
        'saveAuthor': AllErrors & ({
                family: 'SAVE_COMMAND', 
                code: 'READONLY_MIDDLE_TABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NULL_TARGET', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'CANNOT_DISSOCIATE_TARGETS', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_GENERATED_ID', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_KEY_PROP', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_VERSION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'OPTIMISTIC_LOCK_ERROR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NEITHER_ID_NOR_KEY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'REVERSED_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'LONG_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'FAILED_REMOTE_VALIDATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'UNSTRUCTURED_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'TARGET_IS_NOT_TRANSFERABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'INCOMPLETE_PROPERTY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NOT_UNIQUE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_TARGET_ID', 
                readonly [key:string]: any
            })
    }, 
    'bookService': {
        'saveBook': AllErrors & ({
                family: 'SAVE_COMMAND', 
                code: 'READONLY_MIDDLE_TABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NULL_TARGET', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'CANNOT_DISSOCIATE_TARGETS', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_GENERATED_ID', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_KEY_PROP', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_VERSION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'OPTIMISTIC_LOCK_ERROR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NEITHER_ID_NOR_KEY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'REVERSED_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'LONG_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'FAILED_REMOTE_VALIDATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'UNSTRUCTURED_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'TARGET_IS_NOT_TRANSFERABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'INCOMPLETE_PROPERTY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NOT_UNIQUE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_TARGET_ID', 
                readonly [key:string]: any
            }), 
        'saveCompositeBook': AllErrors & ({
                family: 'SAVE_COMMAND', 
                code: 'READONLY_MIDDLE_TABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NULL_TARGET', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'CANNOT_DISSOCIATE_TARGETS', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_GENERATED_ID', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_KEY_PROP', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_VERSION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'OPTIMISTIC_LOCK_ERROR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NEITHER_ID_NOR_KEY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'REVERSED_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'LONG_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'FAILED_REMOTE_VALIDATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'UNSTRUCTURED_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'TARGET_IS_NOT_TRANSFERABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'INCOMPLETE_PROPERTY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NOT_UNIQUE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_TARGET_ID', 
                readonly [key:string]: any
            })
    }, 
    'bookStoreService': {
    }, 
    'treeService': {
        'saveTree': AllErrors & ({
                family: 'SAVE_COMMAND', 
                code: 'READONLY_MIDDLE_TABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NULL_TARGET', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'CANNOT_DISSOCIATE_TARGETS', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_ID_GENERATOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_GENERATED_ID', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_KEY_PROP', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NO_VERSION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'OPTIMISTIC_LOCK_ERROR', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NEITHER_ID_NOR_KEY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'REVERSED_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'LONG_REMOTE_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'FAILED_REMOTE_VALIDATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'UNSTRUCTURED_ASSOCIATION', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'TARGET_IS_NOT_TRANSFERABLE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'INCOMPLETE_PROPERTY', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'NOT_UNIQUE', 
                readonly [key:string]: any
            } | {
                family: 'SAVE_COMMAND', 
                code: 'ILLEGAL_TARGET_ID', 
                readonly [key:string]: any
            })
    }
};
