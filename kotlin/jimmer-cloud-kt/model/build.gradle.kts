import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

repositories {
    mavenCentral()
}

val jimmerVersion: String by rootProject.extra

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(kotlin("stdlib"))

    // For user code
    implementation("org.babyfish.jimmer:jimmer-core:${jimmerVersion}")

    // For generated code
    compileOnly("org.babyfish.jimmer:jimmer-sql-kotlin:${jimmerVersion}")

    // Code generator
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
}

// Without this configuration, gradle command can still run.
// However, Intellij cannot find the generated source.
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
