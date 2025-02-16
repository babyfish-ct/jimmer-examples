package org.babyfish.jimmer.sql.example.kt.web

fun resourceContent(resource: String): String {
    val url = EntityStyleControllerTest::class.java.classLoader.getResource(resource)
        ?: throw IllegalArgumentException("Illegal resource path: $resource")
    return url.readText()
}