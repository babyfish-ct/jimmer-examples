# Jimmer Examples

English | [中文](./README_zh_CN.md)

## Note

In theory, Jimmer's runtime only requires Java 8 *(if REST service documentation or TypeScript auto-generation features are used and the `@FetchBy` annotation is used, then Java 11 is needed to compile the code)*.

However, Java 25 requires a higher version of Gradle, which further affects the Spring-Boot version due to spring-gradle plugin issues, ultimately impacting the actual Java language version that can be adopted. Therefore, examples involving Spring can no longer comprehensively support the full range from Java 8 to Java 25.

To ensure that users with Java 25 can correctly execute these examples, all SpringBoot-related examples use Spring-Boot 3.x and must require Java 17.

## Sub-directories

This is a collection of examples for [Jimmer](https://github.com/babyfish-ct/jimmer), containing the following 4 sub-directories:

|Sub-directory|Description|
|---|---|
|[java](./java/)|Examples in Java|
|[kotlin](./kotlin/)|Examples in Kotlin|
|[rest-client](./rest-client/)|[java/jimmer-sql/](./java/jimmer-sql/) and [kotlin/jimmer-sql-kt/](./kotlin/jimmer-sql-kt/) are two examples of building REST services using Jimmer. Jimmer can automatically generate TypeScript code required by the web client *([documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/client/))*. This is a React-based web application that demonstrates how to use the automatically generated TypeScript code by Jimmer.|
|[env-with-cache](./env-with-cache/)|Jimmer supports powerful caching *([documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/cache/))*. Developers can specify a Spring profile to run [java/jimmer-sql/](./java/jimmer-sql/), [kotlin/jimmer-sql-kt/](./kotlin/jimmer-sql-kt/), [java/jimmer-sql-graphql/](./java/jimmer-sql-graphql/), or [kotlin/jimmer-sql-graphql-kt/](./kotlin/jimmer-sql-graphql-kt/) with caching support. In this running mode, these examples require an external environment, and this sub-directory is the installation directory for those external environments.|

For more details, please refer to the descriptions in the respective sub-directories.

## All Examples

<table>
    <thead>
        <th>Java</th>
        <th>Kotlin</th>
        <th>Description</th>
        <th>Importance</th>
    </thead>
    <tbody>
        <tr>
            <td><a href="./java/jimmer-core">java/jimmer-core</a></td>
            <td><a href="./kotlin/jimmer-core-kt">kotlin/jimmer-core-kt</a></td>
            <td>ORM-independent examples, showcasing <a href="https://github.com/immerjs/immer">immer</a>-style immutable objects</td>
            <td>★★★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-simple">java/jimmer-simple</a></td>
            <td><a href="./kotlin/jimmer-simple-kt">kotlin/jimmer-simple-kt</a></td>
            <td>Using unconditional queries as an example to demonstrate the simplest way to use Jimmer ORM</td>
            <td>★★★★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-sql">java/jimmer-sql</a></td>
            <td><a href="./kotlin/jimmer-sql-kt">kotlin/jimmer-sql-kt</a></td>
            <td>Quickly build REST services using Jimmer. This example showcases most of Jimmer's features and is the most important and fundamental example</td>
            <td>★★★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-sql-graphql">java/jimmer-sql-graphql</a></td>
            <td><a href="./kotlin/jimmer-sql-graphql-kt">kotlin/jimmer-sql-graphql-kt</a></td>
            <td>Quickly build GraphQL services using Jimmer</td>
            <td>★★</td>
        </tr>
        <tr>
            <td><a href="./java/jimmer-cloud">java/jimmer-cloud</a></td>
            <td><a href="./kotlin/jimmer-cloud-kt">kotlin/jimmer-cloud-kt</a></td>
            <td>A microservices project based on Spring Cloud, showcasing Jimmer's remote association</td>
            <td>★</td>
        </tr>
        <tr>
            <td><a href="./java/save-command">java/save-command</a></td>
            <td><a href="./kotlin/save-command-kt">kotlin/save-command-kt</a></td>
            <td>A dedicated example for save commands</td>
            <td>★★★</td>
        </tr>
    </tbody>
</table>


