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

    dependencies{
        implementation("ru.relex:commons:1.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
    implementation(project(":services"))
    implementation(project(":security"))
    implementation("com.amazonaws:aws-java-sdk-athena:1.11.486")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.486")
    implementation("javax.xml.bind:jaxb-api:2.1")
    implementation("org.apache.commons:commons-io:1.3.2")
}
