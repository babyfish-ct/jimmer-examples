# Immutability of Jimmer Objects (Kotlin)

English | [中文](./README_zh_CN.md)

---

## Introduction

> This example explains the immutability of Jimmer objects, which is unrelated to ORM.

Immutable objects have a difficult challenge. For instance, if there is a very deep data structure, it would be difficult to create a new data structure based on it according to some desired modifications, and the difficulty increases dramatically as the depth grows.

Fortunately, there is a sufficiently powerful solution in the JavaScript/TypeScript domain: [immer](https://github.com/immerjs/immer), which can perfectly solve this problem. The way it works is as follows:

1. Open a temporary scope based on the existing immutable data structure.

2. Within this scope, developers can obtain a draft data structure with the same shape and initial values as the original data structure, which can be modified at will, including modifying any deeply nested sub-objects.

3. After the scope ends, the draft data structure will create a new data structure based on the collected modifications. The unmodified parts will be optimized and reuse the old objects.

Immer perfectly combines the advantages of immutable objects and mutable objects, with simple code, powerful features, and excellent performance. Therefore, Jimmer chose to port immer to the JVM ecosystem, and its name is a tribute to immer.

[Documentation link](https://babyfish-ct.github.io/jimmer-doc/docs/object/immutable/).

## Kotlin Symbol Processing

The project depends on the [KSP](https://kotlinlang.org/docs/ksp-overview.html). When opening this project in IntelliJ IDEA for the first time, you may notice that some code that should have been automatically generated is missing. To resolve this, you can choose any of the following methods:

- First, run the `./gradlew build` command in the project directory from the command line to generate the code, and then open the project with IntelliJ IDEA.

- Open the project directly with IntelliJ IDEA, ignoring the IDE errors temporarily. After the dependencies are downloaded, run the project's main method. All IDE errors will automatically disappear, and the application will start correctly.

## Running Method

**After ensuring that the missing code has been generated and recognized by IntelliJ**, you can directly run the `main` method in [App.kt](./src/main/kotlin/org/babyfish/jimmer/example/kt/core/App.kt).

---

[⇧ Back to Parent ⇧](../) | [⇨ Next Example ⇨](../jimmer-simple-kt/)
