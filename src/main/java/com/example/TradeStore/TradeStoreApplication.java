package com.example.TradeStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.example.repository")
@ImportResource("classpath:/integration.xml")
public class TradeStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeStoreApplication.class, args);
    }

}
