package com.library.recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.library.recetas.repository")
@EntityScan("com.library.recetas.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
