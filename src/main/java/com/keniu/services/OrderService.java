package com.keniu.services;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.models.User;

public interface OrderService {
    OrderDto save(User user, CreateOrderRequestDto createOrderRequestDto);
}
