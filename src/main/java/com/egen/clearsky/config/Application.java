package com.egen.clearsky.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class Application {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getMysqlDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate();
    }
}
