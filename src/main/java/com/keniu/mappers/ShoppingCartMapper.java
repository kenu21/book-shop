package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.models.ShoppingCart;
import com.keniu.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(target = "userId", source = "user",
            qualifiedByName = "mapUserToUserId")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    @Named("mapUserToUserId")
    default Long mapUserToUserId(User user) {
        return user.getId();
    }
}
