# 保存指令

[English](./) | 中文

---

## 介绍

保存指令是Jimmer的一个强大功能，接受具备动态性的Jimmer实体所代表的任何数据结构，用一句话保存。

[文档链接](https://babyfish-ct.gitee.io/jimmer-doc/docs/quick-view/save/)。

这一句简单的描述后隐藏着很多种细节，如果对此感兴趣，可以看这个例子，它展示保存指令的一些内部细节。

## Annotation Processor

项目依赖于[KSP](https://kotlinlang.org/docs/ksp-overview.html)，首次使用Intellij打开本项目时，会发现一些本该被自动生成代码并不存在的问题。对此，可以选择以下任何一种方法：
 
-   先用命令行在要打开的项目目录下执行`./gradlew build`命令完成代码生成，再用Intellij打开项目。
 
-   直接用Intelilj代开项目，暂时无视IDE的错误，依赖下载完毕后，直接运行项目单元测试，所有IDE错误将会自动消失，应用也会被正确启动。

## 运行方式

**在确保缺失的代码被已经被生成并为Intellij所识别后**，直接运行任何单元测试即可。

---

[⇦ 上一个例子 ⇦](../jimmer-cloud-kt/README_zh_CN.md) | [⇧ 返回上级 ⇧](../README_zh_CN.md)