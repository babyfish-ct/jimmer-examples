import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.1.20"
	kotlin("plugin.spring") version "2.1.20"
	id("com.google.devtools.ksp") version "2.1.20-2.0.0"
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
}

val jimmerVersion: String by rootProject.extra

group = "org.babyfish.jimmer.example.kt"
version = jimmerVersion
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {

	implementation(project(":repository"))
	implementation(project(":runtime"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("com.graphql-java:graphql-java-extended-scalars:18.1")

	ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	runtimeOnly("com.h2database:h2:2.1.212")
	runtimeOnly("mysql:mysql-connector-java:8.0.30")
	runtimeOnly("org.postgresql:postgresql:42.6.0")
	runtimeOnly("io.lettuce:lettuce-core:6.2.0.RELEASE")
	runtimeOnly("com.github.ben-manes.caffeine:caffeine:2.9.1")
}

// Without this configuration, gradle command can still run.
// However, Intellij cannot find the generated source.
kotlin {
	sourceSets.main {
		kotlin.srcDir("build/generated/ksp/main/kotlin")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
