# Save Command (Java)

English | [中文](./README_zh_CN.md)

---

## Introduction

The save command is a powerful feature of Jimmer, allowing you to save any data structure represented by dynamic Jimmer entities with a single statement.

[Documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/quick-view/save/).

This simple description hides many details, and if you are interested, you can check out this example, which showcases some internal details of the save command.

## Annotation Processor

The project depends on the [Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html). When opening this project in IntelliJ IDEA for the first time, you may notice that some code that should have been automatically generated is missing. To resolve this, you can choose any of the following methods:

- First, run the `./mvnw install` or `./gradlew build` command in the project directory from the command line to generate the code, and then open the project with IntelliJ IDEA.

- Open the project directly with IntelliJ IDEA, ignoring the IDE errors temporarily. After the dependencies are downloaded, run the project's unit tests. All IDE errors will automatically disappear, and the application will start correctly.

## Opening Method

This project supports both `pom.xml` and `build.gradle`, meaning it supports both Maven and Gradle.

When opening this kind of project for the first time, IntelliJ will ask how to open it. Make your choice.

If you want to switch the opening method, exit IntelliJ, delete the hidden `.idea` directory in the project, and then reopen the project with IntelliJ and make a new selection.

## Running Method

**After ensuring that the missing code has been generated and recognized by IntelliJ**, you can directly run any unit test.

---

[⇦ Previous Example ⇦](../jimmer-cloud) | [⇧ Back to Parent ⇧](../)
