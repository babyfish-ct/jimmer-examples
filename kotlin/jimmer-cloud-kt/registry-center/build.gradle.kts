import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.7"
}

val jimmerVersion: String by rootProject.extra
val springCloudVersion: String by rootProject.extra

repositories {
    mavenCentral()
}

dependencies {

    implementation(kotlin("stdlib"))

    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
