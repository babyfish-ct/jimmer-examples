package org.babyfish.jimmer.example.cloud.kt.book

import org.babyfish.jimmer.client.EnableImplicitApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableImplicitApi
@SpringBootApplication
class BookServiceApp

fun main(args: Array<String>) {
    runApplication<BookServiceApp>(*args)
}