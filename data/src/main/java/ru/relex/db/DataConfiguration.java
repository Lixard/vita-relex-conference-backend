package ru.relex.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("ru.relex.db.mapper")
public class DataConfiguration {
}
