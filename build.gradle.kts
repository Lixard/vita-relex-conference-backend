import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "2.2.5.RELEASE"
}

allprojects {
    apply {
        plugin("java")
    }

    group = "ru.relex"
    version = "1.0"

    repositories {
        mavenCentral()
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    dependencies{
        implementation("ru.relex:commons:1.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
    implementation(project(":services"))
    implementation(project(":security"))
    implementation("org.springframework.security:spring-security-config:5.3.0.RELEASE")
    implementation("org.springframework.security:spring-security-web:5.3.0.RELEASE")
    implementation("com.amazonaws:aws-java-sdk-athena:1.11.486")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.486")
    implementation("javax.xml.bind:jaxb-api:2.1")
    implementation("org.apache.commons:commons-io:1.3.2")
}

springBoot {
    mainClassName = "ru.relex.rest.App"
}

val bootJar: BootJar by tasks

bootJar.apply {
    launchScript()
}

