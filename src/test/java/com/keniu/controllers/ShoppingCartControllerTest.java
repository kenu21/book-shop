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
import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.models.User;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ShoppingCartControllerTest extends BaseIntegrationTest {

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
        User user = new User();
        user.setId(2L);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null, // credentials null; use MockFilter; no checking token
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void getShoppingCart_shouldReturnCart() throws Exception {
        mockMvc.perform(get("/cart")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userId").value("2"))
                .andExpect(jsonPath("$.cartItems[0].id").value("1"))
                .andExpect(jsonPath("$.cartItems[0].bookId").value("1"))
                .andExpect(jsonPath("$.cartItems[0].bookTitle")
                    .value("Test Book"))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(4));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void addCartItem_shouldAddItemToCart() throws Exception {
        CreateCartItemRequestDto requestDto = new CreateCartItemRequestDto();
        requestDto.setBookId(1L);
        requestDto.setQuantity(2);

        mockMvc.perform(post("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.userId").value("2"))
            .andExpect(jsonPath("$.cartItems[0].id").exists())
            .andExpect(jsonPath("$.cartItems[0].bookId").value("1"))
            .andExpect(jsonPath("$.cartItems[0].bookTitle")
                .value("Test Book"))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(2));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void updateCartItem_shouldUpdateItemInCart() throws Exception {
        UpdateCarItemRequestDto requestDto = new UpdateCarItemRequestDto();
        requestDto.setQuantity(7);

        mockMvc.perform(put("/cart/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userId").value("2"))
            .andExpect(jsonPath("$.cartItems[0].id").value("1"))
            .andExpect(jsonPath("$.cartItems[0].bookId").value("1"))
            .andExpect(jsonPath("$.cartItems[0].bookTitle")
                .value("Test Book"))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(7));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void deleteCartItem_shouldRemoveItemFromCart() throws Exception {
        mockMvc.perform(delete("/cart/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userId").value("2"))
                .andExpect(jsonPath("$.cartItems").isEmpty());
    }
}
