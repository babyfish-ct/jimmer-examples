# Jimmer对象的不可变性 (Java)

[English](./) | 中文

---

## 介绍

> 这个例子讲解Jimmer对象的不可变性, 和ORM无关。

不可变对象有一个难点。比如，现有一个很深的数据结构，那么基于它按照一些修改的愿望创建出新的数据结构会很困难，难度随着深度的变大急剧增加。

幸运的是，JavaScript/TypeScript领域存在一个足够强大的方案: [immer](https://github.com/immerjs/immer)，可以完美解决这个问题。该方案工作方式如下

1.  基于现有不可变数据结构开启一个临时作用域。

2.  在这个作用域内，开发人人员可得到一个draft数据结构，该数据结构的形状和初始值和原数据结构完全一致，且可以被随意修改，包括修改任意深的子对象。

3.  作用域结束后，draft数据结构会利用收集到的修改行为创建另外一个新的数据结构。其中，未被修改的局部会被优化处理，复用以前的旧对象。

Immer完美结合了不可变对象和可变对象的优点，代码简单、功能强大、性能卓越。因此，Jimmer选择为JVM生态移植了immer，项目名称也是对其致敬。

[文档链接](https://babyfish-ct.gitee.io/jimmer-doc/docs/object/immutable/)。

## Annotation Processor

项目依赖于[Annotation Processor](https://www.jetbrains.com/help/idea/annotation-processors-support.html)，首次使用Intellij打开本项目时，会发现一些本该被自动生成代码并不存在的问题。对此，可以选择以下任何一种方法：
 
-   先用命令行在要打开的项目目录下执行./mvnw install或./gradlew build命令完成代码生成，再用Intellij打开项目。
 
-   直接用Intelilj代开项目，暂时无视IDE的错误，依赖下载完毕后，直接运行项目的main方法或单元测试 (save-command/save-command-kt以单元测试演示功能)，所有IDE错误将会自动消失，应用也会被正确启动。

## 打开方式

该项目具备pom.xml和build.gradle，即，maven/gradle双支持。

首次打开这种项目时，Intellij会询问以何种方式打开，做出选择即可。

如果要切换打开方式，退出Intellij，删除项目下的隐藏目录.idea，再用Intellij打开，重新选择即可。

## 运行方式

**在确保缺失的代码被已经被生成并为Intellij所识别后**，直接运行[App.java](./src/main/java/org/babyfish/jimmer/example/core/App.java)中的`main`方法即可。

---

[⇧ 返回上级 ⇧](../README_zh_CN.md) | [⇨ 下一个例子 ⇨](../jimmer-sql/README_zh_CN.md)
