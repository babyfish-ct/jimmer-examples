export const SaveErrorCode_CONSTANTS = [
    'READONLY_MIDDLE_TABLE', 
    'NULL_TARGET', 
    'CANNOT_DISSOCIATE_TARGETS', 
    'NO_ID_GENERATOR', 
    'ILLEGAL_ID_GENERATOR', 
    'ILLEGAL_GENERATED_ID', 
    'ILLEGAL_INTERCEPTOR_BEHAVIOR', 
    'EMPTY_OBJECT', 
    'NO_KEY_PROPS', 
    'NO_KEY_PROP', 
    'NO_NON_ID_PROPS', 
    'NO_VERSION', 
    'OPTIMISTIC_LOCK_ERROR', 
    'ALREADY_EXISTS', 
    'NEITHER_ID_NOR_KEY', 
    'REVERSED_REMOTE_ASSOCIATION', 
    'LONG_REMOTE_ASSOCIATION', 
    'FAILED_REMOTE_VALIDATION', 
    'UNSTRUCTURED_ASSOCIATION', 
    'TARGET_IS_NOT_TRANSFERABLE', 
    'INCOMPLETE_PROPERTY', 
    'NOT_UNIQUE', 
    'ILLEGAL_TARGET_ID'
] as const;
export type SaveErrorCode = typeof SaveErrorCode_CONSTANTS[number];