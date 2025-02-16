# Jimmer ORM最简单示范 (Kotlin)

[English](./) | 中文

---

## 介绍

这个例子的功能非常简单，通过无条件查询，以简单的方式展示如何使用Jimmer。

## Kotlin Symbol Processing

项目依赖于[KSP](https://kotlinlang.org/docs/ksp-overview.html)，首次使用Intellij打开本项目时，会发现一些本该被自动生成代码并不存在的问题。对此，可以选择以下任何一种方法：
 
-   先用命令行在要打开的项目目录下执行`./gradlew build`命令完成代码生成，再用Intellij打开项目。
 
-   直接用Intelilj代开项目，暂时无视IDE的错误，依赖下载完毕后，直接运行项目的main方法或单元测试 (save-command/save-command-kt以单元测试演示功能)，所有IDE错误将会自动消失，应用也会被正确启动。

## 运行方式

1.   **在确保缺失的代码被已经被生成并为Intellij所识别后**，直接运行[App.kt](./src/main/kotlin/org/babyfish/jimmer/example/kt/core/App.kt)中的`main`方法即可。

2.   使用浏览器访问http://localhost:8080/openapi.html
---

[⇦ 上一个例子 ⇦](../jimmer-core/README_zh_CN.md) | [⇧ 返回上级 ⇧](../README_zh_CN.md) | [⇨ 下一个例子 ⇨](../jimmer-sql-kt/README_zh_CN.md)
