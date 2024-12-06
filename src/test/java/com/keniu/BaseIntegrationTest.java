package com.keniu;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public abstract class BaseIntegrationTest {

    private static final CustomMySqlContainer MYSQL_CONTAINER = CustomMySqlContainer.getInstance();

    static {
        MYSQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void registerDatabaseProperties(DynamicPropertyRegistry registry) {
        MYSQL_CONTAINER.registerProperties(registry);
    }
}
