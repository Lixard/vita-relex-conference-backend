package ru.relex.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.relex.security.SecurityConfiguration;
import ru.relex.services.ServicesConfiguration;

@SpringBootApplication
@Import({
        ServicesConfiguration.class,
        SecurityConfiguration.class,
})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
