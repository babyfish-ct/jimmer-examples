import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.1.20"
	id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

val jimmerVersion: String by rootProject.extra

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.babyfish.jimmer:jimmer-sql-kotlin:${jimmerVersion}")
	ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
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
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
