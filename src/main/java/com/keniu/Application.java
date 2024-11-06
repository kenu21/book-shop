package com.keniu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
