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

/**
 * Mapper interface for converting between
 * {@link Book} entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic generation of implementation.
 */
@Mapper(config = MapperConfig.class)
public interface BookMapper {

    /**
     * Converts a {@link Book} entity to a {@link BookDto}.
     *
     * @param book the book entity to convert
     * @return the corresponding BookDto
     */
    @Mapping(target = "categoryIds", source = "categories",
            qualifiedByName = "mapCategoriesToCategoryIds")
    BookDto toDto(Book book);

    /**
     * Converts a {@link CreateBookRequestDto} to a {@link Book} entity.
     *
     * @param createBookRequestDto the DTO containing data to create a new book
     * @return the newly created Book entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", expression = "java(false)")
    @Mapping(target = "categories", source = "categoryIds",
            qualifiedByName = "mapCategoryIdsToCategories")
    Book toModel(CreateBookRequestDto createBookRequestDto);

    /**
     * Converts a {@link Book} entity to a {@link BookDtoWithoutCategoryIds}.
     * This excludes the category IDs from the resulting DTO.
     *
     * @param book the book entity to convert
     * @return the corresponding BookDtoWithoutCategoryIds
     */
    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    /**
     * Maps a set of {@link Category} entities to a set of their IDs.
     * Used in mapping Book entities to DTOs.
     *
     * @param categories the set of Category entities
     * @return a set of category IDs
     */
    @Named("mapCategoriesToCategoryIds")
    default Set<Long> mapCategoriesToCategoryIds(Set<Category> categories) {
        if (categories.isEmpty()) {
            return Collections.emptySet();
        }
        return categories.stream()
            .map(Category::getId)
            .collect(Collectors.toSet());
    }

    /**
     * Maps a set of category IDs to a set of {@link Category} entities.
     * Used in mapping DTOs to Book entities.
     *
     * @param categoryIds the set of category IDs
     * @return a set of Category entities
     */
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
