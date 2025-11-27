plugins {
    id("common-conventions")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.bootJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
