package com.keniu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    /**
     * The main method that serves as the entry point
     * for the Spring Boot application.
     *
     * @param args command line arguments, should not be null
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * CommandLineRunner bean that executes code after the application starts.
     *
     * @return CommandLineRunner implementation
     */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }
}
