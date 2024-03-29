package org.babyfish.jimmer.example.kt.core

import org.babyfish.jimmer.example.kt.core.model.*
import org.babyfish.jimmer.kt.new
import java.time.LocalDateTime

fun main(args: Array<String>) {
    println("Book demo")
    println("-------------------")
    bookDemo()
    println()

    println("TreeNode demo")
    println("-------------------")
    treeNodeDemo()
    println()

    println("Sweeter TreeNode demo")
    println("-------------------")
    sweeterTreeNodeDemo()
    println()
}

private fun bookDemo() {

    /*
     * First step, create new object from scratch
     */
    val book = new(Book::class).by {
        name = "book"
        store().name = "store"
        authors().addBy {
            name = "author-1"
        }
        authors().addBy {
            name = "author-2"
        }
    }

    /*
     * Second step, make some "changes" based on the existing object to get a new object.
     *
     * `val newBook = book.copy {...}` is shorthand for
     * `val newBook = new(Book::class).by(book) {...}`
     */
    val newBook = book.copy {

        name += "!"
        lastModifiedTime = LocalDateTime.now()

        store().name += "!"
        store().lastModifiedTime = LocalDateTime.now()

        for (author in authors()) {
            author.name += "!"
            author.lastModifiedTime = LocalDateTime.now()
        }
    }

    println("book: $book")
    println("newBook: $newBook")
}

private fun treeNodeDemo() {

    /*
     * First step, create new object from scratch
     */
    val treeNode = new(TreeNode::class).by {
        name = "Root"
        childNodes().addBy {
            name = "Food"
            childNodes().addBy {
                name = "Drinks"
                childNodes().addBy {
                    name = "Cococola"
                }
                childNodes().addBy {
                    name = "Fanta"
                }
            }
        }
    }

    /*
     * Second step, make some "changes" based on the existing object to get a new object.
     */
    val newTreeNode = new(TreeNode::class).by(treeNode) {
        childNodes()[0] // Food
            .childNodes()[0] // Drinks
            .childNodes()[0] // Cococola
            .name += " plus"
    }

    println("treeNode: $treeNode")
    println("newTreeNode: $newTreeNode")
}

private fun sweeterTreeNodeDemo() {

    /*
     * First step, create new object from scratch
     */
    val treeNode = TreeNode {
        name = "Root"
        childNodes = listOf(
            TreeNode {
                name = "Food"
                childNodes = listOf(
                    TreeNode {
                        name = "Drinks"
                        childNodes = listOf(
                            TreeNode{
                                name = "Cococola"
                            },
                            TreeNode {
                                name = "Fanta"
                            }
                        )
                    }
                )
            }
        )
    }

    /*
     * Second step, make some "changes" based on the existing object to get a new object.
     *
     * `val newTreeNode = treeNode.copy {...}` is shorthand for
     * `val newTreeNode = new(TreeNode::class).by(treeNode) {...}`
     */
    val newTreeNode = treeNode.copy {
        childNodes()[0] // Food
            .childNodes()[0] // Drinks
            .childNodes()[0] // Cococola
            .name += " plus"
    }

    println("treeNode: $treeNode")
    println("newTreeNode: $newTreeNode")
}
