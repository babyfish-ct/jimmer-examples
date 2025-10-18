import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.1.20"
	kotlin("plugin.spring") version "2.1.20"
}

val jimmerVersion: String by rootProject.extra
val springBootVersion: String by rootProject.extra

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {

	implementation(project(":model"))
	implementation(project(":repository"))

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
	implementation("org.redisson:redisson-spring-boot-starter:3.52.0")
	implementation("org.springframework.kafka:spring-kafka:3.3.4")
	implementation("org.apache.kafka:connect-api:0.10.0.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
