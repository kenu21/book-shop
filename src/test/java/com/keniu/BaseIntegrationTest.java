package com.keniu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {

    private static final CustomMySqlContainer MYSQL_CONTAINER = CustomMySqlContainer.getInstance();

    static {
        MYSQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void registerDatabaseProperties(DynamicPropertyRegistry registry) {
        MYSQL_CONTAINER.registerProperties(registry);
    }

    @AfterAll
    static void stopContainer() {
        MYSQL_CONTAINER.stop();
    }
}
