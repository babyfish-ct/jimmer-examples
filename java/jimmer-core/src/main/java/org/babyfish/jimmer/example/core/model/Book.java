package org.babyfish.jimmer.example.core.model;

import org.babyfish.jimmer.Immutable;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Immutable
public interface Book {

    String name();

    @Nullable
    BookStore store();

    int price();

    LocalDateTime lastModifiedTime();

    List<Author> authors();
}
