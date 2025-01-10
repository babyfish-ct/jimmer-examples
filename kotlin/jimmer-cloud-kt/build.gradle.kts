val jimmerVersion by extra { "0.9.44" }

// Jimmer remote associations require ANTLR
val antlrVersion by extra { "4.13.2"}

val springCloudVersion by extra { "2021.0.3" }

allprojects {
    group = "org.babyfish.jimmer.example.cloud.kt"
    version = jimmerVersion
}
