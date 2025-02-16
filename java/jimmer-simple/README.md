# Simplest Demonstration of Jimmer ORM (Java)

English | [中文](./README_zh_CN.md)

---

## Introduction

This example has very simple functionality, demonstrating how to use Jimmer through unconditional queries in a simple way.

## Annotation Processor

The project depends on [Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html). When first opening this project in IntelliJ, you may notice that some automatically generated code is missing. To resolve this, you can choose either of the following methods:

- First use the command line to execute `./mvnw install` or `./gradlew build` in the project directory to complete code generation, then open the project with IntelliJ.

- Open the project directly with IntelliJ, temporarily ignore the IDE errors, and after dependencies are downloaded, run the project's main method or unit tests (save-command/save-command-kt demonstrates functionality through unit tests). All IDE errors will automatically disappear, and the application will start correctly.

## Opening Method

This project includes both pom.xml and build.gradle, meaning it supports both maven and gradle.

When first opening such a project, IntelliJ will ask how you want to open it. Simply make your choice.

If you want to switch the opening method, exit IntelliJ, delete the hidden .idea directory under the project, then reopen with IntelliJ and make your selection again.

## How to Run

1. **After ensuring the missing code has been generated and is recognized by IntelliJ**, simply run the `main` method in [App.java](./src/main/java/org/babyfish/jimmer/example/core/App.java).

2. Use a browser to visit http://localhost:8080/openapi.html

---

[⇦ Previous Example ⇦](../jimmer-core) | [⇧ Return to Parent ⇧](..) | [⇨ Next Example ⇨](../jimmer-sql)
