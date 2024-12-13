package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    Order toModel(CreateOrderRequestDto createOrderRequestDto);

    void updateOrderFromDto(
            UpdateOrderRequestDto updateOrderRequestDto,
            @MappingTarget Order order);
}
