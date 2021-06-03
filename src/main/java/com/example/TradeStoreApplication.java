package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EntityScan("com.example.repository")
@ImportResource(value = {"classpath:integration.xml"})
public class TradeStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeStoreApplication.class, args);
    }

}
