plugins {
    id("spring-conventions")
    id("test-conventions")
}

dependencies {

    implementation(projects.repository)
    implementation(projects.runtime)

    ksp(libs.jimmer.ksp)

    implementation(libs.web)

    runtimeOnly(libs.h2)
    runtimeOnly(libs.mysql)
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.lettuce.core)
    runtimeOnly(libs.caffeine)
}
