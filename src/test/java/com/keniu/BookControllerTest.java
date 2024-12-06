package com.keniu;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookControllerTest extends BaseIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockFilter()))
            .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void findAll_shouldReturnBooks() throws Exception {
        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title")
                    .value("Test Book"))
                .andExpect(jsonPath("$.content[0].author")
                    .value("Author1"))
                .andExpect(jsonPath("$.content[0].isbn")
                    .value("123-4567890123"))
                .andExpect(jsonPath("$.content[0].price")
                    .value(10.99))
                .andExpect(jsonPath("$.content[0].description")
                    .value("Description1"))
                .andExpect(jsonPath("$.content[0].coverImage")
                    .value("cover1.jpg"));
    }
}
