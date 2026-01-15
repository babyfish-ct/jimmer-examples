rootProject.name = "jimmer-sql-kt-new"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include("model")
findProject(":model")?.name = "model"

include("repository")
findProject(":repository")?.name = "repository"

include("runtime")
findProject(":runtime")?.name = "runtime"


include("service")
findProject(":service")?.name = "service"
