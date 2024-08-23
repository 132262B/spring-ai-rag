import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
}

val applicationVersion : String by project
val projectGroup : String by project

java {
    sourceCompatibility = JavaVersion.VERSION_17
}


allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

//repositories {
//    mavenCentral()
//    maven { url = uri("https://repo.spring.io/milestone") }
//}
//
//extra["springAiVersion"] = "1.0.0-M1"
//
//dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//
//    // JPA
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//
//    // mysql
//    runtimeOnly("com.mysql:mysql-connector-j")
//
//    // Spring Ai
//    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
//    implementation("org.springframework.ai:spring-ai-elasticsearch-store-spring-boot-starter")
//    implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")
//
//    // PDF BOX
//    implementation("org.apache.pdfbox:pdfbox:2.0.27")
//
//
//    // https://mvnrepository.com/artifact/group.springframework.ai/spring-ai-pdf-document-reader
////    implementation("group.springframework.ai:spring-ai-pdf-document-reader:1.1.0")
//
//}

//dependencyManagement {
//    imports {
//        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
//    }
//}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.oshai:kotlin-logging-jvm:6.0.2")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.mockk:mockk:1.13.12")
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}