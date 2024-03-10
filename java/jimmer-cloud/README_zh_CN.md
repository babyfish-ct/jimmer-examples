# Jimmer的远程实体关联 (Java)

[English](./) | 中文

## 介绍

开发人员可以选择为Jimmer的实体类型指定微服务，如果隶属于不同微服务的实体类型之间存在关联，就叫远程关联。

Jimmer支持查询任意形状的数据结构，如果数据结构的形状跨越了多个服务，那么Jimmer会自动从不同的微服务查询数据的给个部分并最终拼接成一个整体。

>   远程关联只是在有限时间内为微服务体系开发的首个功能，目的在于向开发人员证明ORM技术体系在微服务技术体系下依旧可以很强大。
> 
>   等Jimmer逐渐完善发布1.0正式版本后，将会在微服务技术体系添加更多的功能，为该体系下更多的繁琐的细节进行抽象和简化。

[文档链接](https://babyfish-ct.gitee.io/jimmer-doc/docs/spring/spring-cloud)

## Annotation Processor

项目依赖于[Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html)，首次使用Intellij打开本项目时，会发现一些本该被自动生成代码并不存在的问题。对此，可以选择以下任何一种方法：
 
-   先用命令行在要打开的项目目录下执行`./mvnw install`或`./gradlew build`命令完成代码生成，再用Intellij打开项目。
 
-   直接用Intelilj代开项目，暂时无视IDE的错误，依赖下载完毕后，直接运行项目的main方法，所有IDE错误将会自动消失，应用也会被正确启动。

## 打开方式

该项目具备`pom.xml`和`build.gradle`，即，maven/gradle双支持。

首次打开这种项目时，Intellij会询问以何种方式打开，做出选择即可。

如果要切换打开方式，退出Intellij，删除项目下的隐藏目录`.idea`，再用Intellij打开，重新选择即可。

## 运行方式

**在确保缺失的代码被已经被生成并为Intellij所识别后**，即可运行例子。

这是一个微服务例子，所以需要启动多个应用。

幸运的是，所有依赖于数据库的服务都使用H2内嵌数据库，所以无需按照任何环境，直接运行。

启动类|启用优先级|访问方式|
|---|---|---|
|[registry-center/.../RegistryCenterApp.java](./registry-center/src/main/java/org/babyfish/jimmer/example/cloud/registry/RegistryCenterApp.java)|率先启动|`http://localhost:7000`|
|[store-service/.../StoreServiceApp.java](./store-service/src/main/java/org/babyfish/jimmer/example/cloud/store/StoreServiceApp.java)|在注册中心启动成功后启动|`http://localhost:7001/openapi.html`|
|[book-service/.../BookServiceApp.java](./book-service/src/main/java/org/babyfish/jimmer/example/cloud/book/BookServiceApp.java)|在注册中心启动成功后启动|`http://localhost:7002/openapi.html`|
|[author-service/.../AuthorServiceApp.java](./author-service/src/main/java/org/babyfish/jimmer/example/cloud/author/AuthorServiceApp.java)|在注册中心启动成功后启动|`http://localhost:7003/openapi.html`|

启动完成后，可以访问上面表格中后面三个的服务openapi。

以`http://localhost:7001/openapi.html`为例子，该服务支持两个`GET`请求：

-   `/store/{id}`

    该查询只需访问当前服务的数据，可用于验证当前服务的正确性。

-   `/store/{id}/detail`

    该查询必须整合所有服务的数据才能返回数据，用于演示本例子真正想展示的东西：Jimmer远程关联。

    >   如果微服务之间通信失败，可能是过早访问的问题，稍微等待几秒即可。
    >   
    >   如果稍加等待后，如果微服务之间通信依旧失败，可以访问`http://localhost:7000`查看注册中心各服务的机器名称，将此名称配置到本机的hosts文件中。

其他服务的使用方法类似，这里不再赘述。

---

[⇦ 上一个例子 ⇦](../jimmer-sql-graphql/README_zh_CN.md) | [⇧ 返回上级 ⇧](../README_zh_CN.md) | [⇨ 下一个例子 ⇨](../save-command/README_zh_CN.md)
