package com.gmail.sasha.myproject.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.gmail.sasha.myproject.config",
                               "com.gmail.sasha.myproject.service",
                               "com.gmail.sasha.myproject.dao",
                               "com.gmail.sasha.myproject.web"})
@PropertySources({
        @PropertySource(value = "classpath:config.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:pagepath.properties", ignoreResourceNotFound = true),
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
