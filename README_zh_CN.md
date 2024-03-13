# Jimmer例子

[English](./) | 中文

## 子目录

这是[Jimmer](https://github.com/babyfish-ct/jimmer)的例子合集，存在如下4个子目录：

|子目录|描述|
|---|---|
|[java](./java//README_zh_CN.md)|Java语言的例子|
|[kotlin](./kotlin//README_zh_CN.md)|Kotlin语言的例子|
|[rest-client](./rest-client/)|[java/jimmer-sql/](./java/jimmer-sql/README_zh_CN.md)和[java/jimmer-sql-kt/](./kotlin/jimmer-sql-kt/README_zh_CN.md)是两个使用Jimmer构建REST的例子，Jimmer能够自动生成Web客户端所需的TypeScript代码 *([文档链接](https://babyfish-ct.gitee.io/jimmer-doc/docs/client/))*。这是一个基于React的Web应用，展示如何使用Jimmer自动生成的TypeScripit代码|
|[env-with-cache](./env-with-cache/)|Jimmer支持强大的缓存 *([文档链接](https://babyfish-ct.gitee.io/jimmer-doc/docs/cache/))*。开发人员可以通过指定spring profile的方式让[java/jimmer-sql/](./java/jimmer-sql/README_zh_CN.md)、[java/jimmer-sql-kt/](./kotlin/jimmer-sql-kt/README_zh_CN.md)、[java/jimmer-sql-graphql/](./java/jimmer-sql-graphql/README_zh_CN.md)或[java/jimmer-sql-graphql-kt/](./kotlin/jimmer-sql-graphql-kt/README_zh_CN.md)以支持缓存的方式运行，在这种运行模式下下，这些例子需要外部环境，这个子目录就是这些外部环境的安装目录|

更多的细节，请参考到各子目录的描述。

## 所有例子

<table>
    <thead>
        <th>Java</th>
        <th>Kotlin</th>
        <th>描述</th>
        <th>重要性</th>
    </thead>
    <tbody>
        <tr>
            <td><a href="./java/jimmer-core/README_zh_CN.md">java/jimmer-core</a></td>
            <td><a href="./kotlin/jimmer-core-kt">kotlin/jimmer-core-kt</a></td>
            <td>ORM无关示例，展示<a href="https://github.com/immerjs/immer">immer</a>风格的不可变对象</td>
            <td>★★★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-sql/README_zh_CN.md">java/jimmer-sql</a></td>
            <td><a href="./kotlin/jimmer-sql-kt">kotlin/jimmer-sql-kt</a></td>
            <td>利用Jimmer快速构建REST服务。这个例子展示了大部分Jimmer的功能，是最重要的最基础的例子</td>
            <td>★★★★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-sql-graphql/README_zh_CN.md">java/jimmer-sql-graphql</a></td>
            <td><a href="./kotlin/jimmer-sql-graphql-kt">kotlin/jimmer-sql-graphql-kt</a></td>
            <td>利用Jimmer快速构建GraphQL服务</td>
            <td>★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-cloud/README_zh_CN.md">java/jimmer-cloud</a></td>
            <td><a href="./kotlin/jimmer-cloud-kt">kotlin/jimmer-cloud-kt</a></td>
            <td>基于Spring Cloud的微服务项目，展示Jimmer的远程关联</td>
            <td>★</td>
        </tr>
        <tr>
            <td><a href="./java/save-command/README_zh_CN.md">java/save-command</a></td>
            <td><a href="./kotlin/save-command-kt">kotlin/save-command-kt</a></td>
            <td>针对保存指令的专项例子</td>
            <td>★★★</td>
        </tr>
    </tbody>
</table>
