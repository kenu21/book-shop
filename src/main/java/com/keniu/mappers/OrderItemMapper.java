package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.OrderItemDto;
import com.keniu.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {

    @Mapping(target = "bookId", source = "book.id")
    OrderItemDto toDto(OrderItem orderItem);
}
