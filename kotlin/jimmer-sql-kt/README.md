# Building REST Services with Jimmer (Kotlin)

English | [中文](./README_zh_CN.md)

## Introduction

This example involves multiple features.

> The three core features: [Query Arbitrary Shape](https://babyfish-ct.github.io/jimmer-doc/docs/quick-view/fetch/), [Save Arbitrary Shape](https://babyfish-ct.github.io/jimmer-doc/docs/quick-view/fetch/), and [Arbitrary Dynamic Query](https://babyfish-ct.github.io/jimmer-doc/docs/quick-view/dsl/) are bound to be used, so they are not listed in the table below.

<table>
<thead>
<tr>
<th>Sub-project</th>
<th>Related Feature Documentation Links</th>
</tr>
</thead>
<tbody>
<tr>
<td rowspan="3">model</td>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/overview/apt-ksp">Using KSP in the project defining entities</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/formula">Defining formula-calculated properties</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/transient">Defining complex calculated properties</a>
</td>
</tr>
<tr>
<td rowspan="2">repository</td>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/spring/repository/">Spring Data-style Repository</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/query/paging/">Intelligent pagination</a>
</td>
</tr>
<tr>
<tr>
<td rowspan="4">rtunime</td>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/cache/">Cache support</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/query/global-filter/">Global filter</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/mutation/draft-interceptor">Pre-save interceptor</a>
</td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/mapping/advanced/calculated/transient">Providing Resolver for calculated properties</a></td>
</tr>
<tr>
<td rowspan="3">service</td>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/object/view/dto-language">DTO language</a>
</td>
</tr>
<tr>
<td><a href="https://babyfish-ct.github.io/jimmer-doc/docs/overview/apt-ksp">Using KSP in the project defining DTOs</a></td>
</tr>
<tr>
<td>
<a href="https://babyfish-ct.github.io/jimmer-doc/docs/client/">Automatically generating OpenAPI documentation and TypeScript code</a>
</td>
</tr>
</tbody>
</table>

## Kotlin Symbol Processing

The project depends on the [KSP](https://kotlinlang.org/docs/ksp-overview.html). When opening this project in IntelliJ IDEA for the first time, you may notice that some code that should have been automatically generated is missing. To resolve this, you can choose any of the following methods:

-   First, run the `./gradlew build` command in the project directory from the command line to generate the code, and then open the project with IntelliJ IDEA.

-   Open the project directly with IntelliJ IDEA, ignoring the IDE errors temporarily. After the dependencies are downloaded, run the project's main method. All IDE errors will automatically disappear, and the application will start correctly.

## Running Method

**After ensuring that the missing code has been generated and recognized by IntelliJ**, you can run the example.

The current example supports two running modes:

- Non-caching mode

    This is the default mode, provided for users who want to quickly run and experience the example.

    This mode uses the H2 embedded database, so there is no need to install any external environment. You can directly run [service/.../App.kt](./service/src/main/kotlin/org/babyfish/jimmer/sql/example/kt/App.kt).

- Caching mode

    This mode is provided to showcase the powerful [Jimmer caching](https://babyfish-ct.github.io/jimmer-doc/docs/cache/) and requires external dependencies.

    To better demonstrate its universality, Jimmer provides two external environments.

    - MySQL + Maxwell

        First, install Docker, then execute [env-with-cache/maxwell/install.sh](../../env-with-cache/maxwell/install.sh) to complete the installation.

        Finally, run [service/.../App.kt](./service/src/main/kotlin/org/babyfish/jimmer/sql/example/kt/App.kt) using the Spring profile parameter `-Dspring.profiles.active=maxwell` or `--spring.profiles.active=maxwell`.

    - Postgres + Debezium

        First, install Docker, then execute [env-with-cache/debezium/install.sh](../../env-with-cache/debezium/install.sh) to complete the installation.

        Finally, run [service/.../App.kt](./service/src/main/kotlin/org/babyfish/jimmer/sql/example/kt/App.kt) using the Spring profile parameter `-Dspring.profiles.active=debezium` or `--spring.profiles.active=debezium`.

    > You only need to choose one of the two environments. If you install both, the Docker containers may encounter errors due to insufficient memory. If you really want to do this, it is recommended to allocate 3GB of memory for Docker.

Regardless of which running mode you use to start the project, you can access the service through `http://localhost:8080/openapi.html`.

## Tenant Identity

To demonstrate the [global filter](https://babyfish-ct.github.io/jimmer-doc/docs/query/global-filter/), the `Book` type in this example uses a multi-tenant mode.

If you want to access the service with a tenant's identity, you need to set the `tenant` header in the HTTP request, with a value of `a` or `b`.

> By default, there are only two tenants in the data: `a` and `b`. Of course, users can add more tenants.

- For query operations, this HTTP request header is optional. If it is not provided, it will query the books of all tenants.

- For data saving operations, this HTTP request header is required. If it is not provided, the server-side will throw an exception.

You can find a button in the top right corner of `http://localhost:8080/openapi.html` to set the tenant:

![tenant](../../__internal/swagger-authorize.webp)

In the pop-up dialog, enter `a` or `b` and confirm.

---

[⇦ Previous Example ⇦](../jimmer-core-kt) | [⇧ Back to Parent ⇧](../) | [⇨ Next Example ⇨](../jimmer-sql-graphql-kt/)
