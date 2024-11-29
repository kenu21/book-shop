package com.keniu.controllers;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.models.User;
import com.keniu.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
@Tag(name = "Order management", description = "Endpoints for managing orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "CreateOrder")
    public OrderDto makeOrder(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return orderService.save(user, createOrderRequestDto);
    }

}
