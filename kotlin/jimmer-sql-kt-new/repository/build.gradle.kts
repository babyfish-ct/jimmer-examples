plugins {
    id("spring-conventions")
}

allOpen {
    annotation("org.springframework.stereotype.Repository")
}

dependencies {

    api(projects.model)

    api(libs.jimmer.spring.boot.starter)

}

tasks.bootJar {
    enabled = false
}