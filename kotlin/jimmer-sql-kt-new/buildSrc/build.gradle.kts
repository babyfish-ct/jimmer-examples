plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
    }
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    api(gradleApi())
    api("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${libs.versions.kotlin.get()}")
    api("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:${libs.versions.kotlin.get()}")
    api("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${libs.versions.kotlin.get()}")
    api("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${libs.versions.ksp.get()}")
    api("io.spring.gradle:dependency-management-plugin:${libs.versions.spring.dependency.management.get()}")
    api("org.springframework.boot:org.springframework.boot.gradle.plugin:${libs.versions.springboot.get()}")
}
