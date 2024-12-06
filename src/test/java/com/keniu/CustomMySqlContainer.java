package com.keniu;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MySQLContainer;

public class CustomMySqlContainer extends MySQLContainer<CustomMySqlContainer> {

    private static final String DB_IMAGE = "mysql:8";

    private static CustomMySqlContainer mysqlContainer;

    private CustomMySqlContainer() {
        super(DB_IMAGE);
    }

    public static synchronized CustomMySqlContainer getInstance() {
        if (mysqlContainer == null) {
            mysqlContainer = new CustomMySqlContainer();
        }
        return mysqlContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("TEST_DB_URL", this.getJdbcUrl());
        System.setProperty("TEST_DB_USERNAME", this.getUsername());
        System.setProperty("TEST_DB_PASSWORD", this.getPassword());
    }

    public void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", this::getJdbcUrl);
        registry.add("spring.datasource.username", this::getUsername);
        registry.add("spring.datasource.password", this::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
    }
}
