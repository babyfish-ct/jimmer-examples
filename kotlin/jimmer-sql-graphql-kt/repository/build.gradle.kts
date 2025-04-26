import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.1.20"
	kotlin("plugin.allopen") version "2.1.20"
}

val jimmerVersion: String by rootProject.extra

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

allOpen {
	annotation("org.springframework.stereotype.Repository")
}

dependencies {

	api(project(":model"))

	api("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}") {
		exclude(group = "org.antlr", module = "antlr4-runtime")
	}

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
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
