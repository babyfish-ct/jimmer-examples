# 使用Jimmer构建GraphQL服务

[English](./) | 中文

## 介绍

这里例子涉及到多个功能

> 三大核心功能：[查询任意形状](https://babyfish-ct.gitee.io/jimmer-doc/docs/quick-view/fetch/)，
[保存任意形状](https://babyfish-ct.gitee.io/jimmer-doc/docs/quick-view/fetch/)，
[任意复杂查询](https://babyfish-ct.gitee.io/jimmer-doc/docs/quick-view/dsl/)必然会被使用，故而不罗列在下表中。

<table>
<thead>
<tr>
<th>子项目</th>
<th>相关功能文档链接</th>
</tr>
</thead>
<tbody>
<tr>
<td rowspan="3">model</td>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/overview/apt-ksp">在定义实体的项目中使用APT</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/mapping/advanced/calculated/formula">定义公式计算属性</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/mapping/advanced/calculated/transient">定义复杂计算属性</a>
</td>
</tr>
<tr>
<td>repository</td>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/spring/repository/">Spring Data风格的Repository</a>
</td>
</tr>
<tr>
</tr>
<tr>
<tr>
<td rowspan="4">rtunime</td>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/cache/">缓存支持</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/query/global-filter/">全局过滤器</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/mutation/draft-interceptor">保存前拦截器</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/mapping/advanced/calculated/transient">为计算属性提供Resolver</a></td>
</tr>
<tr>
<td rowspan="3">service</td>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/object/view/dto-language">DTO语言</a>
</td>
</tr>
<tr>
<td><a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/overview/apt-ksp">在定义DTO文件的项目中使用APT</a></td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.gitee.io/jimmer-doc/docs/graphql/">基于Jimmer构建GraphQL服务</a>
</td>
</tr>
</tbody>
</table>

## Annotation Processor

项目依赖于[Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html)，首次使用Intellij打开本项目时，会发现一些本该被自动生成代码并不存在的问题。对此，可以选择以下任何一种方法：
 
-   先用命令行在要打开的项目目录下执行./mvnw install或./gradlew build命令完成代码生成，再用Intellij打开项目。
 
-   直接用Intelilj代开项目，暂时无视IDE的错误，依赖下载完毕后，直接运行项目的main方法或单元测试 (save-command/save-command-kt以单元测试演示功能)，所有IDE错误将会自动消失，应用也会被正确启动。

## 打开方式

该项目具备pom.xml和build.gradle，即，maven/gradle双支持。

首次打开这种项目时，Intellij会询问以何种方式打开，做出选择即可。

如果要切换打开方式，退出Intellij，删除项目下的隐藏目录.idea，再用Intellij打开，重新选择即可。

## 运行方式

**在确保缺失的代码被已经被生成并为Intellij所识别后**，即可运行例子。

当前例子支持两种运行方式

### 两种运行方式

-   无缓存模式

    这是默认的模式，为想要快速运行和体验例子的用户提供。
    
    该模式采用H2内嵌数据库，所以无需安装任何外部环境，直接运行[service/.../App.java](./service/src/main/java/org/babyfish/jimmer/sql/example/App.java)即可。

-   有缓存模式

    该模式为展示强大的[Jimmer缓存](https://babyfish-ct.gitee.io/jimmer-doc/docs/cache/)而提供，需要外部依赖。
    
    为了更好地示范其普适性，Jimmer提供两种外部环境。

    -   MySQL + Maxwell

        先安装docker，然后执行[env-with-cache/maxwell/install.sh](../../env-with-cache/maxwell/install.sh)完成安装。

        最后，使用参数spring profile参数`-Dspring.profiles.active=maxwell`或`--spring.profiles.active=maxwell`运行[service/.../App.java](./service/src/main/java/org/babyfish/jimmer/sql/example/App.java)即可。

    -   Postgres + Debezium

        先安装docker，然后执行[env-with-cache/debezium/install.sh](../../env-with-cache/debezium/install.sh)完成安装。

        最后，使用参数spring profile参数`-Dspring.profiles.active=debezium`或`--spring.profiles.active=debezium`运行[service/.../App.java](./service/src/main/java/org/babyfish/jimmer/sql/example/App.java)即可。

    >   两种环境选择一种即可，如果都安装，可能因内存不足而导致docker容器发生错误。如果真的要这么做，建议为docker分配3GB内存。

---

[⇦ 上一个例子 ⇦](../jimmer-sql) | [⇧ 返回上级 ⇧](../) | [⇨ 下一个例子 ⇨](../jimmer-sql-cloud)