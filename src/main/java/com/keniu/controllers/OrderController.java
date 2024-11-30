package com.keniu.controllers;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.dto.OrderItemDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.models.User;
import com.keniu.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "getOrders")
    public Page<OrderDto> getOrders(@AuthenticationPrincipal User user, Pageable pageable) {
        return orderService.findAll(user, pageable);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "updateOrder")
    public OrderDto updateOrder(@PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        return orderService.update(id, updateOrderRequestDto);
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "getItemsForOrder")
    public Set<OrderItemDto> getItemsForOrder(@PathVariable Long id) {
        return orderService.findItemsForOrder(id);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "getItemForOrder")
    public OrderItemDto getItemsForOrder(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        return orderService.findItemForOrder(orderId, itemId);
    }
}
