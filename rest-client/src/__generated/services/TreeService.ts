import type {Executor} from '../';
import type {TreeNodeDto} from '../model/dto/';
import type {FlatTreeNodeView, RecursiveTreeInput} from '../model/static/';

export class TreeService {
    
    constructor(private executor: Executor) {}
    
    readonly deleteTree: (options: TreeServiceOptions['deleteTree']) => Promise<
        void
    > = async(options) => {
        let _uri = '/tree/';
        _uri += encodeURIComponent(options.id);
        return (await this.executor({uri: _uri, method: 'DELETE'})) as Promise<void>;
    }
    
    readonly findNodeById: (options: TreeServiceOptions['findNodeById']) => Promise<
        TreeNodeDto['TreeService/DETAIL_FETCHER'] | undefined
    > = async(options) => {
        let _uri = '/tree/node/';
        _uri += encodeURIComponent(options.id);
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<TreeNodeDto['TreeService/DETAIL_FETCHER'] | undefined>;
    }
    
    readonly findRootTrees: (options: TreeServiceOptions['findRootTrees']) => Promise<
        ReadonlyArray<TreeNodeDto['TreeService/RECURSIVE_FETCHER']>
    > = async(options) => {
        let _uri = '/tree/roots/recursive';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.rootName;
        if (_value !== undefined && _value !== null) {
            _uri += _separator
            _uri += 'rootName='
            _uri += encodeURIComponent(_value);
            _separator = '&';
        }
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<ReadonlyArray<TreeNodeDto['TreeService/RECURSIVE_FETCHER']>>;
    }
    
    readonly flatNodes: (options: TreeServiceOptions['flatNodes']) => Promise<
        ReadonlyArray<FlatTreeNodeView>
    > = async(options) => {
        let _uri = '/tree/flatNodes';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.name;
        if (_value !== undefined && _value !== null) {
            _uri += _separator
            _uri += 'name='
            _uri += encodeURIComponent(_value);
            _separator = '&';
        }
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<ReadonlyArray<FlatTreeNodeView>>;
    }
    
    readonly saveTree: (options: TreeServiceOptions['saveTree']) => Promise<
        TreeNodeDto['TreeService/RECURSIVE_FETCHER']
    > = async(options) => {
        let _uri = '/tree/root/recursive';
        return (await this.executor({uri: _uri, method: 'PUT', body: options.body})) as Promise<TreeNodeDto['TreeService/RECURSIVE_FETCHER']>;
    }
}

export type TreeServiceOptions = {
    'flatNodes': {
        readonly name?: string | undefined
    }, 
    'findRootTrees': {
        readonly rootName?: string | undefined
    }, 
    'findNodeById': {
        readonly id: number
    }, 
    'saveTree': {
        readonly body: RecursiveTreeInput
    }, 
    'deleteTree': {
        readonly id: number
    }
}
