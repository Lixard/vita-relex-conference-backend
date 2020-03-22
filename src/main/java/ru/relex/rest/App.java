package ru.relex.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.relex.security.SecurityConfiguration;
import ru.relex.services.ServicesConfiguration;

@SpringBootApplication(
        scanBasePackages = "ru.relex.rest"
)
@Import({
        ServicesConfiguration.class,
        SecurityConfiguration.class,
})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
