dependencies {
    implementation("org.hibernate.validator:hibernate-validator:6.1.2.Final")
    implementation("org.springframework.boot:spring-boot-autoconfigure:2.2.5.RELEASE")
    implementation("org.springframework.security:spring-security-web:5.3.0.RELEASE")
    implementation(project(":data"))
    implementation("org.mapstruct:mapstruct:1.3.1.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.3.1.Final")
    implementation("com.amazonaws:aws-java-sdk-athena:1.11.486")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.486")
    implementation("javax.xml.bind:jaxb-api:2.1")
    implementation("org.apache.commons:commons-io:1.3.2")
}
