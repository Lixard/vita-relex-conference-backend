dependencies {
    runtimeOnly("org.postgresql:postgresql:42.2.11")
    runtimeOnly("org.liquibase:liquibase-core:3.8.0")
    runtimeOnly("org.springframework:spring-jdbc:5.2.4.RELEASE")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.1")
    implementation("org.springframework.boot:spring-boot-autoconfigure:2.2.5.RELEASE")
}