package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.BookDto;
import com.keniu.dto.BookDtoWithoutCategoryIds;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.models.Book;
import com.keniu.models.Category;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categoryIds", source = "categories",
            qualifiedByName = "mapCategoriesToCategoryIds")
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    @Mapping(target = "categories", source = "categoryIds",
            qualifiedByName = "mapCategoryIdsToCategories")
    Book toModel(CreateBookRequestDto createBookRequestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @Named("mapCategoriesToCategoryIds")
    default Set<Long> mapCategoriesToCategoryIds(Set<Category> categories) {
        if (categories.isEmpty()) {
            return Collections.emptySet();
        }
        return categories.stream()
            .map(Category::getId)
            .collect(Collectors.toSet());
    }

    @Named("mapCategoryIdsToCategories")
    default Set<Category> mapCategoryIdsToCategories(Set<Long> categoryIds) {
        if (categoryIds == null) {
            return Collections.emptySet();
        }
        return categoryIds.stream()
            .map(id -> {
                Category category = new Category();
                category.setId(id);
                return category;
            })
            .collect(Collectors.toSet());
    }
}
