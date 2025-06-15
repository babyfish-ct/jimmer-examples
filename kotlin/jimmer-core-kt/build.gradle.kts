plugins {
    kotlin("jvm") version "2.1.20"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

val jimmerVersion = "0.9.92"

group = "org.babyfish.jimmer.example.kt"
version = jimmerVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.babyfish.jimmer:jimmer-core-kotlin:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
}

// Without this configuration, gradle command can still run.
// However, Intellij cannot find the generated source.
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
