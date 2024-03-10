# Remote Entity Association in Jimmer (Java)

English | [中文](./README_zh_CN.md)

## Introduction

Developers can choose to specify a microservice for Jimmer entity types. If entity types belonging to different microservices are associated, it is called a remote association.

Jimmer supports querying data structures of arbitrary shapes. If the shape of the data structure spans multiple services, Jimmer will automatically query the respective parts of the data from different microservices and eventually assemble them into a whole.

> Remote association is the first feature developed for the microservices architecture, aiming to demonstrate to developers that ORM technology can still be powerful in the microservices architecture.
>
> After Jimmer gradually matures and releases the official 1.0 version, more features will be added to the microservices architecture, abstracting and simplifying more tedious details in this architecture.

[Documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/spring/spring-cloud)

## Annotation Processor

The project depends on the [Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html). When opening this project in IntelliJ IDEA for the first time, you may notice that some code that should have been automatically generated is missing. To resolve this, you can choose any of the following methods:

- First, run the `./mvnw install` or `./gradlew build` command in the project directory from the command line to generate the code, and then open the project with IntelliJ IDEA.

- Open the project directly with IntelliJ IDEA, ignoring the IDE errors temporarily. After the dependencies are downloaded, run the project's main method. All IDE errors will automatically disappear, and the application will start correctly.

## Opening Method

This project supports both `pom.xml` and `build.gradle`, meaning it supports both Maven and Gradle.

When opening this kind of project for the first time, IntelliJ will ask how to open it. Make your choice.

If you want to switch the opening method, exit IntelliJ, delete the hidden `.idea` directory in the project, and then reopen the project with IntelliJ and make a new selection.

## Running Method

**After ensuring that the missing code has been generated and recognized by IntelliJ**, you can run the example.

This is a microservices example, so you need to start multiple applications.

Fortunately, all services that depend on a database use the H2 embedded database, so there is no need to set up any environment; you can run them directly.

Start Class | Priority | Access Method
|---|---|---|
|[registry-center/.../RegistryCenterApp.java](./registry-center/src/main/java/org/babyfish/jimmer/example/cloud/registry/RegistryCenterApp.java) | Start first | `http://localhost:7000`|
|[store-service/.../StoreServiceApp.java](./store-service/src/main/java/org/babyfish/jimmer/example/cloud/store/StoreServiceApp.java) | Start after the registry center is up | `http://localhost:7001/openapi.html`|
|[book-service/.../BookServiceApp.java](./book-service/src/main/java/org/babyfish/jimmer/example/cloud/book/BookServiceApp.java) | Start after the registry center is up | `http://localhost:7002/openapi.html`|
|[author-service/.../AuthorServiceApp.java](./author-service/src/main/java/org/babyfish/jimmer/example/cloud/author/AuthorServiceApp.java) | Start after the registry center is up | `http://localhost:7003/openapi.html`|

After starting, you can access the OpenAPI of the last three services in the table above.

Taking `http://localhost:7001/openapi.html` as an example, this service supports two `GET` requests:

- `/store/{id}`

    This query only needs to access the data of the current service, which can be used to verify the correctness of the current service.

- `/store/{id}/detail`

    This query must integrate data from all services to return data, demonstrating what this example is really trying to showcase: Jimmer's remote association.

    > If the communication between microservices fails, it may be due to accessing too early. Wait a few seconds.
    >
    > If the communication between microservices still fails after waiting, you can visit `http://localhost:7000` to view the machine names of each service in the registry center and configure this name in your local hosts file.

The usage of other services is similar, so it will not be elaborated here.

---

[⇦ Previous Example ⇦](../jimmer-sql-graphql) | [⇧ Back to Parent ⇧](../) | [⇨ Next Example ⇨](../save-command/)
