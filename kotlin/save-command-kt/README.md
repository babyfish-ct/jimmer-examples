# Save Command

English | [中文](./README_zh_CN.md)

---

## Introduction

The save command is a powerful feature of Jimmer, allowing you to save any data structure represented by dynamic Jimmer entities with a single statement.

[Documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/quick-view/save/).

This simple description hides many details, and if you are interested, you can check out this example, which showcases some internal details of the save command.

## Kotlin Symbol Processing

The project depends on the [KSP](https://kotlinlang.org/docs/ksp-overview.html). When opening this project in IntelliJ IDEA for the first time, you may notice that some code that should have been automatically generated is missing. To resolve this, you can choose any of the following methods:

- First, run the `./gradlew build` command in the project directory from the command line to generate the code, and then open the project with IntelliJ IDEA.

- Open the project directly with IntelliJ IDEA, ignoring the IDE errors temporarily. After the dependencies are downloaded, run the project's unit tests. All IDE errors will automatically disappear, and the application will start correctly.

## Running Method

**After ensuring that the missing code has been generated and recognized by IntelliJ**, you can directly run any unit test.

---

[⇦ Previous Example ⇦](../jimmer-cloud-kt) | [⇧ Back to Parent ⇧](../)
