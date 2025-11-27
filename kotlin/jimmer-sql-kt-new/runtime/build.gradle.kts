plugins {
	id("spring-conventions")
}

dependencies {

	implementation(projects.model)
	implementation(projects.repository)

	implementation(libs.web)
	implementation(libs.redisson)
	implementation(libs.bundles.kafak)
}

tasks.bootJar {
	enabled = false
}

