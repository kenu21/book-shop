package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CategoryDto;
import com.keniu.dto.CreateCategoryRequestDto;
import com.keniu.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);

    void updateCategoryFromDto(
            CreateCategoryRequestDto createCategoryRequestDto,
            @MappingTarget Category category);
}
