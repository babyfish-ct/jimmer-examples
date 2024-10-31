package org.babyfish.jimmer.sql.example.cfg;

import org.babyfish.jimmer.sql.example.model.*;
import org.babyfish.jimmer.sql.exception.SaveException;
import org.babyfish.jimmer.sql.runtime.ExceptionTranslator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotUniqueExceptionTranslator implements ExceptionTranslator<SaveException.NotUnique> {

    @Override
    public @Nullable Exception translate(SaveException.@NotNull NotUnique ex, @NotNull Args args) {
        if (ex.isMatched(TreeNodeProps.PARENT, TreeNodeProps.NAME)) {
            throw new IllegalArgumentException(
                    "The tree node with parent id \"" +
                            ex.getValue(TreeNodeProps.PARENT).id() +
                            "\" and name \"" +
                            ex.getValue(TreeNodeProps.NAME) +
                            "\" already exists"
            );
        }
        if (ex.isMatched(BookStoreProps.NAME)) {
            throw new IllegalArgumentException(
                    "The book store with name \"" +
                            ex.getValue(BookStoreProps.NAME) +
                            "\" already exists"
            );
        }
        if (ex.isMatched(BookProps.NAME, BookProps.EDITION)) {
            throw new IllegalArgumentException(
                    "The book with name \"" +
                            ex.getValue(BookProps.NAME) +
                            "\" and edition " +
                            ex.getValue(BookProps.EDITION) +
                            " already exists"
            );
        }
        if (ex.isMatched(AuthorProps.FIRST_NAME, AuthorProps.LAST_NAME)) {
            throw new IllegalArgumentException(
                    "The book with first name \"" +
                            ex.getValue(AuthorProps.FIRST_NAME) +
                            "\" and last name " +
                            ex.getValue(AuthorProps.LAST_NAME) +
                            " already exists"
            );
        }
        return null;
    }
}
