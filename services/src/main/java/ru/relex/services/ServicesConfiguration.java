package ru.relex.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.db.DataConfiguration;

@Configuration
@Import({DataConfiguration.class})
public class ServicesConfiguration {
}
