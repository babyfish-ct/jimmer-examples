plugins {
	id("common-conventions")
}

dependencies {
	ksp(libs.jimmer.ksp)
	implementation(libs.jimmer.sql.kotlin)
	implementation(libs.jackson.kotlin)
}

kotlin {
	sourceSets.main {
		kotlin.srcDir("build/generated/ksp/main/kotlin")
	}
}
