# Simplest Demonstration of Jimmer ORM (Kotlin)

English | [中文](./README_zh_CN.md)

---

## Introduction

This example has very simple functionality, demonstrating how to use Jimmer through unconditional queries in a simple way.

## Kotlin Symbol Processing

The project depends on [KSP](https://kotlinlang.org/docs/ksp-overview.html). When first opening this project in IntelliJ, you may notice that some automatically generated code is missing. To resolve this, you can choose either of the following methods:

- First use the command line to execute `./gradlew build` in the project directory to complete code generation, then open the project with IntelliJ.

- Open the project directly with IntelliJ, temporarily ignore the IDE errors, and after dependencies are downloaded, run the project's main method or unit tests (save-command/save-command-kt demonstrates functionality through unit tests). All IDE errors will automatically disappear, and the application will start correctly.

## How to Run

1. **After ensuring the missing code has been generated and is recognized by IntelliJ**, simply run the `main` method in [App.kt](./src/main/kotlin/org/babyfish/jimmer/example/kt/core/App.kt).

2. Use a browser to visit http://localhost:8080/openapi.html

---

[⇦ Previous Example ⇦](../jimmer-core-kt/) | [⇧ Return to Parent ⇧](../) | [⇨ Next Example ⇨](../jimmer-sql-kt)
