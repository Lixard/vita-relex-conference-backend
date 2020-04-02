dependencies {
    implementation(project(":services"))
    implementation(project(":data"))
    implementation("org.springframework.security:spring-security-web:5.3.0.RELEASE")
    implementation("org.springframework.security:spring-security-config:5.3.0.RELEASE")
    compileOnly("jakarta.servlet:jakarta.servlet-api:4.0.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.10.3")
}
