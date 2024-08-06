import org.jetbrains.kotlin.gradle.utils.API

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":presentation-user"))
    implementation(project(":web"))
}