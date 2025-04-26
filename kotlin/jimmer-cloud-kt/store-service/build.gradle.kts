import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
}

val jimmerVersion: String by rootProject.extra

// Jimmer remote associations require ANTLR
val antlrVersion: String by rootProject.extra

val springCloudVersion: String by rootProject.extra

repositories {
    mavenCentral()
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

    implementation(project(":model"))

    // OpenApi Generator
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:$jimmerVersion")

    // If Jimmer's remote association is used,
    // please manually add the ANTLR runtime dependency.
    // Otherwise, it is unnecessary
    runtimeOnly("org.antlr:antlr4-runtime:$antlrVersion")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")

    runtimeOnly("com.h2database:h2:2.1.212")
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
