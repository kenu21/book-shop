package com.keniu.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keniu.BaseIntegrationTest;
import com.keniu.MockFilter;
import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.models.Status;
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
class OrderControllerTest extends BaseIntegrationTest {

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
    @Sql(scripts = "classpath:add-order.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void getOrders_shouldReturnOrders() throws Exception {
        mockMvc.perform(get("/orders")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value("1"))
            .andExpect(jsonPath("$.content[0].userId").value("2"))
            .andExpect(jsonPath("$.content[0].orderItems[0].id").value("1"))
            .andExpect(jsonPath("$.content[0].orderItems[0].bookId").value("1"))
            .andExpect(jsonPath("$.content[0].orderItems[0].quantity").value("4"))
            .andExpect(jsonPath("$.content[0].orderDate").exists())
            .andExpect(jsonPath("$.content[0].total").value(100.00))
                .andExpect(jsonPath("$.content[0].status").value("PENDING"));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void makeOrder_shouldCreateOrder() throws Exception {
        CreateOrderRequestDto createOrderRequestDto = new CreateOrderRequestDto();
        createOrderRequestDto.setShippingAddress("123 Test St");

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createOrderRequestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.userId").value("2"))
            .andExpect(jsonPath("$.orderItems[0].id").exists())
            .andExpect(jsonPath("$.orderItems[0].bookId").value("1"))
            .andExpect(jsonPath("$.orderItems[0].quantity").value("4"))
            .andExpect(jsonPath("$.orderDate").exists())
            .andExpect(jsonPath("$.total").value(100.00))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:add-order.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void updateOrder_shouldUpdateOrderStatus() throws Exception {
        UpdateOrderRequestDto updateOrderRequestDto = new UpdateOrderRequestDto();
        updateOrderRequestDto.setStatus(Status.COMPLETED);

        mockMvc.perform(patch("/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateOrderRequestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.userId").value("2"))
            .andExpect(jsonPath("$.orderItems[0].id").value("1"))
            .andExpect(jsonPath("$.orderItems[0].bookId").value("1"))
            .andExpect(jsonPath("$.orderItems[0].quantity").value("4"))
            .andExpect(jsonPath("$.orderDate").exists())
            .andExpect(jsonPath("$.total").value(100.00))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:add-order.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void getItemsForOrder_shouldReturnOrderItems() throws Exception {
        mockMvc.perform(get("/orders/1/items")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].bookId").value("1"))
                .andExpect(jsonPath("$[0].quantity").value(4));
    }

    @Test
    @Sql(scripts = "classpath:add-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:add-book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(
            scripts = "classpath:add-cart-item.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(scripts = "classpath:add-order.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void getItemForOrder_shouldReturnSpecificOrderItem() throws Exception {
        mockMvc.perform(get("/orders/1/items/1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.bookId").value("1"))
                .andExpect(jsonPath("$.quantity").value(4));
    }
}
