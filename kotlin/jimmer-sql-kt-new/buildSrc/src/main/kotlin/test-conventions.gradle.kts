import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("common-conventions")
    id("spring-conventions")
}

val libs = the<LibrariesForLibs>()

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.bundles.unit.test)
}

tasks.test {
    useJUnitPlatform()
} 