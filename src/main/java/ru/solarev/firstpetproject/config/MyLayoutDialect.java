package ru.solarev.firstpetproject.config;


import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class MyLayoutDialect {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(null, false);
    }
}
