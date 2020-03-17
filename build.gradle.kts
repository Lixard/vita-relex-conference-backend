plugins {
    java
    application
}

allprojects {
    apply {
        plugin("java")
    }

    group = "ru.relex"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }
}

application {
    mainClassName = "ru.relex.App"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
    implementation(project(":data"))
}