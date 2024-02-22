package org.babyfish.jimmer.example.cloud.kt.store

import org.babyfish.jimmer.client.EnableImplicitApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableImplicitApi
@SpringBootApplication
class StoreServiceApp

fun main(args: Array<String>) {
    runApplication<StoreServiceApp>(*args)
}