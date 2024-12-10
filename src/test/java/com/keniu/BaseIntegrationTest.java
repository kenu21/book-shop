package com.keniu;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class BaseIntegrationTest {

    private static final String DB_IMAGE = "mysql:8";
    private static final MySQLContainer<?> MYSQL_CONTAINER;

    static {
        MYSQL_CONTAINER = new MySQLContainer<>(DB_IMAGE);
        MYSQL_CONTAINER.start();

        System.setProperty("TEST_DB_URL", MYSQL_CONTAINER.getJdbcUrl());
        System.setProperty("TEST_DB_USERNAME", MYSQL_CONTAINER.getUsername());
        System.setProperty("TEST_DB_PASSWORD", MYSQL_CONTAINER.getPassword());
    }

    @DynamicPropertySource
    static void registerDatabaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
    }
}
