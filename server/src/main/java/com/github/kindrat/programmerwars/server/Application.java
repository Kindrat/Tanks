package com.github.kindrat.programmerwars.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.github.kindrat.programmerwars.server.persistence"})
@EnableTransactionManagement
public class Application {
    public static void main(String... args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
