import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    id("com.google.devtools.ksp")
}

val libs = the<LibrariesForLibs>()

repositories {
    mavenLocal()
    mavenCentral()
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${libs.versions.kotlin.get()}")
}

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    toolchain { languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get())) }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.java.get())
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xjvm-default=all",
            "-verbose"
        )
    }
    jvmToolchain(libs.versions.java.get().toInt())
}