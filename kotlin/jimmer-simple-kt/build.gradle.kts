import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

val jimmerVersion = "0.9.120"
group = "org.babyfish.jimmer.example.kt"
version = jimmerVersion

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

    implementation("org.springframework.boot:spring-boot-starter-web")

    /*
     * Optional runtime dependency: jimmer-client-swagger.
     *
     * Generally speaking, even if you remove the dependency,
     * the functionality is normal, that why it is optional.
     *
     * Without this dependency, the `/openapi.html` uses external links:
     *     'https://unpkg.com/swagger-ui-dist@${version}/swagger-ui.css' and
     *     'https://unpkg.com/swagger-ui-dist@${version}/swagger-ui-bundle.js'.
     *
     * However, You may be in a restricted network environment so that
     * such external links are not accessible. If you are in trouble with this,
     * please add this runtime dependency to use the embedded css and js.
     */
    runtimeOnly("org.babyfish.jimmer:jimmer-client-swagger:${jimmerVersion}")

    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly("com.h2database:h2:2.1.212")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.2")
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
