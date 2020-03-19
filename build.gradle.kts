plugins {
    java
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

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
    implementation(project(":data"))
}