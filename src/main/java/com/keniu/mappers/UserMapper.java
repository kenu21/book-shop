package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;
import com.keniu.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between
 * {@link User} entities and their corresponding DTOs.
 */
@Mapper(config = MapperConfig.class)
public interface UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to convert
     * @return the corresponding UserDto
     */
    UserDto toDto(User user);

    /**
     * Converts a {@link CreateUserRequestDto} to a {@link User} entity.
     *
     * @param createUserRequestDto the DTO containing data to create a new user
     * @return the newly created User entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    User toModel(CreateUserRequestDto createUserRequestDto);
}
