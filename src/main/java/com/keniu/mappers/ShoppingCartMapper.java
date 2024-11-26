package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.models.ShoppingCart;
import com.keniu.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper interface for converting between
 * {@link ShoppingCart} entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic generation of implementation.
 * Includes mapping logic for related entities like {@link User}.
 */
@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    /**
     * Converts a {@link ShoppingCart} entity to a {@link ShoppingCartDto}.
     *
     * @param shoppingCart the shopping cart entity to convert
     * @return the corresponding ShoppingCartDto
     */
    @Mapping(target = "userId", source = "user",
            qualifiedByName = "mapUserToUserId")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    /**
     * Maps a {@link User} entity to its ID.
     * Used in mapping ShoppingCart entities to DTOs.
     *
     * @param user the user entity
     * @return the ID of the user
     */
    @Named("mapUserToUserId")
    default Long mapUserToUserId(User user) {
        return user.getId();
    }
}
