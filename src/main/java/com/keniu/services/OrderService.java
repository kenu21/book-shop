package com.keniu.services;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto save(User user, CreateOrderRequestDto createOrderRequestDto);

    Page<OrderDto> findAll(User user, Pageable pageable);

    OrderDto update(Long id, UpdateOrderRequestDto updateOrderRequestDto);
}
