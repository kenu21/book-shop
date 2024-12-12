package com.keniu.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keniu.BaseIntegrationTest;
import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserLoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void register_shouldCreateUser() throws Exception {
        CreateUserRequestDto createUserRequestDto = createUserRequestDto();

        mockMvc.perform(post("/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserRequestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.email").value("test@example.com"))
            .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.shippingAddress").value("123 Test St."));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void login_shouldAuthenticateUser() throws Exception {
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setEmail("test2@example.com");
        userLoginRequestDto.setPassword("securePassword123");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLoginRequestDto)))
            .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    private CreateUserRequestDto createUserRequestDto() {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setEmail("test@example.com");
        createUserRequestDto.setPassword("securePassword123");
        createUserRequestDto.setRepeatPassword("securePassword123");
        createUserRequestDto.setFirstName("Test");
        createUserRequestDto.setLastName("User");
        createUserRequestDto.setShippingAddress("123 Test St.");
        return createUserRequestDto;
    }
}
