import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
