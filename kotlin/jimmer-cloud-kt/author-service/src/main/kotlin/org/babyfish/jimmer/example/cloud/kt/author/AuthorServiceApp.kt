package org.babyfish.jimmer.example.cloud.kt.author

import org.babyfish.jimmer.client.EnableImplicitApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableImplicitApi
@SpringBootApplication
class AuthorServiceApp

fun main(args: Array<String>) {
    runApplication<AuthorServiceApp>(*args)
}