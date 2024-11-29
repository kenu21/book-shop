package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.models.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toModel(CreateOrderRequestDto createOrderRequestDto);
    Order toModel(UpdateOrderRequestDto updateOrderRequestDto);
}
