export type TreeNodeDto = {
    'TreeService/DETAIL_FETCHER': {
        readonly id: number;
        readonly createdTime: string;
        readonly modifiedTime: string;
        readonly name: string;
        readonly parent?: TreeNodeDto['TreeService/DETAIL_FETCHER@parent'] | undefined;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/DETAIL_FETCHER@childNodes']> | undefined;
    }, 
    'TreeService/RECURSIVE_FETCHER': {
        readonly id: number;
        readonly createdTime: string;
        readonly modifiedTime: string;
        readonly name: string;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/RECURSIVE_FETCHER']> | undefined;
    }, 
    'TreeService/DETAIL_FETCHER@parent': {
        readonly id: number;
        readonly createdTime: string;
        readonly modifiedTime: string;
        readonly name: string;
        readonly parent?: TreeNodeDto['TreeService/DETAIL_FETCHER@parent'] | undefined;
    }, 
    'TreeService/DETAIL_FETCHER@childNodes': {
        readonly id: number;
        readonly createdTime: string;
        readonly modifiedTime: string;
        readonly name: string;
        readonly childNodes?: ReadonlyArray<TreeNodeDto['TreeService/DETAIL_FETCHER@childNodes']> | undefined;
    }
}
