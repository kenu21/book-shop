package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CategoryDto;
import com.keniu.dto.CreateCategoryRequestDto;
import com.keniu.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between
 * {@link Category} entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic generation of implementation.
 */
@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    /**
     * Converts a {@link Category} entity to a {@link CategoryDto}.
     *
     * @param category the Category entity to convert
     * @return the corresponding CategoryDto
     */
    CategoryDto toDto(Category category);

    /**
     * Converts a {@link CreateCategoryRequestDto} to a {@link Category} entity.
     *
     * @param createCategoryRequestDto the DTO containing data to create a new category
     * @return the newly created Category entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);
}
