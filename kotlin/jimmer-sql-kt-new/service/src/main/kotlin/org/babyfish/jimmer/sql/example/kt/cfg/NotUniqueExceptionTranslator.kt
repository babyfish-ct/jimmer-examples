package org.babyfish.jimmer.sql.example.kt.cfg

import org.babyfish.jimmer.sql.example.kt.model.*
import org.babyfish.jimmer.sql.exception.SaveException.NotUnique
import org.babyfish.jimmer.sql.kt.get
import org.babyfish.jimmer.sql.kt.isMatched
import org.babyfish.jimmer.sql.runtime.ExceptionTranslator
import org.springframework.stereotype.Component

@Component
class NotUniqueExceptionTranslator() : ExceptionTranslator<NotUnique> {
    override fun translate(ex: NotUnique, args: ExceptionTranslator.Args?): Exception? {
        if (ex.isMatched(TreeNode::parent, TreeNode::name)) {
            throw IllegalArgumentException(
                "The tree node with parent id \"" +
                    ex[TreeNode::parent]?.id +
                    "\" and name \"" +
                    ex[TreeNode::name] +
                    "\" already exists"
            )
        }
        if (ex.isMatched(BookStore::name)) {
            throw IllegalArgumentException(
                "The book store with name \"" +
                    ex[BookStore::name] +
                    "\" already exists"
            )
        }
        if (ex.isMatched(Book::name, Book::edition)) {
            throw IllegalArgumentException(
                ("The book with name \"" +
                    ex[Book::name] +
                    "\" and edition " +
                    ex[Book::edition] +
                    " already exists")
            )
        }
        if (ex.isMatched(Author::firstName, Author::lastName)) {
            throw IllegalArgumentException(
                ("The book with first name \"" +
                    ex[Author::firstName] +
                    "\" and last name " +
                    ex[Author::lastName] +
                    " already exists")
            )
        }
        return null
    }
}

