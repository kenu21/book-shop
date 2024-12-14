package com.keniu.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keniu.BaseIntegrationTest;
import com.keniu.MockFilter;
import com.keniu.dto.CreateBookRequestDto;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class BookControllerTest extends BaseIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockFilter()))
            .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void findAll_shouldReturnBooks() throws Exception {
        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id")
                .value("1"))
                .andExpect(jsonPath("$.content[0].title")
                .value("Test Book"))
                .andExpect(jsonPath("$.content[0].author")
                .value("Author1"))
                .andExpect(jsonPath("$.content[0].isbn")
                .value("123-4567890123"))
                .andExpect(jsonPath("$.content[0].price")
                .value(25))
                .andExpect(jsonPath("$.content[0].description")
                .value("Description1"))
                .andExpect(jsonPath("$.content[0].categoryIds[0]")
                .value("1"))
                .andExpect(jsonPath("$.content[0].coverImage")
                .value("cover1.jpg"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void getById_shouldReturnBook() throws Exception {
        mockMvc.perform(get("/books/1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.title").value("Test Book"))
            .andExpect(jsonPath("$.author").value("Author1"))
            .andExpect(jsonPath("$.isbn").value("123-4567890123"))
            .andExpect(jsonPath("$.price").value(25))
            .andExpect(jsonPath("$.description").value("Description1"))
            .andExpect(jsonPath("$.categoryIds[0]").value("1"))
                .andExpect(jsonPath("$.coverImage").value("cover1.jpg"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void save_shouldCreateBook() throws Exception {
        CreateBookRequestDto createBookRequestDto = createBookRequestDto();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookRequestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.title").value("New Book"))
            .andExpect(jsonPath("$.author").value("New Author"))
            .andExpect(jsonPath("$.isbn").value("987-6543210987"))
            .andExpect(jsonPath("$.price").value(15.99))
            .andExpect(jsonPath("$.description").value("New Description"))
            .andExpect(jsonPath("$.categoryIds").exists())
                .andExpect(jsonPath("$.coverImage").value("newcover.jpg"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void update_shouldUpdateBook() throws Exception {
        CreateBookRequestDto createBookRequestDto = createBookRequestDtoToUpdate();

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookRequestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.title").value("Updated Book"))
            .andExpect(jsonPath("$.author").value("Updated Author"))
            .andExpect(jsonPath("$.isbn").value("321-6549873210"))
            .andExpect(jsonPath("$.price").value(20.99))
            .andExpect(jsonPath("$.description").value("Updated Description"))
            .andExpect(jsonPath("$.categoryIds").exists())
                .andExpect(jsonPath("$.coverImage").value("newcover.jpg"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void deleteById_shouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    private CreateBookRequestDto createBookRequestDto() {
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        createBookRequestDto.setTitle("New Book");
        createBookRequestDto.setAuthor("New Author");
        createBookRequestDto.setIsbn("987-6543210987");
        createBookRequestDto.setPrice(BigDecimal.valueOf(15.99));
        createBookRequestDto.setDescription("New Description");
        createBookRequestDto.setCoverImage("newcover.jpg");
        return createBookRequestDto;
    }

    private CreateBookRequestDto createBookRequestDtoToUpdate() {
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        createBookRequestDto.setTitle("Updated Book");
        createBookRequestDto.setAuthor("Updated Author");
        createBookRequestDto.setIsbn("321-6549873210");
        createBookRequestDto.setPrice(BigDecimal.valueOf(20.99));
        createBookRequestDto.setDescription("Updated Description");
        createBookRequestDto.setCoverImage("newcover.jpg");
        return createBookRequestDto;
    }
}
