package org.babyfish.jimmer.example.core.model;

import org.babyfish.jimmer.Immutable;

import java.time.LocalDateTime;
import java.util.List;

@Immutable
public interface BookStore {

    String name();

    LocalDateTime lastModifiedTime();

    List<Book> books();
}
