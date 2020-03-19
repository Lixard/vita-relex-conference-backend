package ru.relex.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.db.DataConfiguration;

@Configuration
@ComponentScan({
        "ru.relex.services.mapstruct",
        "ru.relex.services.service"
})
@Import({DataConfiguration.class})
public class ServicesConfiguration {
}
