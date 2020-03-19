dependencies {
    implementation("org.springframework.boot:spring-boot-autoconfigure:2.2.5.RELEASE")
    implementation(project(":data"))
    implementation("org.mapstruct:mapstruct:1.3.1.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.3.1.Final")
}