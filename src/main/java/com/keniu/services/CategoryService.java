package com.keniu.services;

import com.keniu.dto.CategoryDto;
import com.keniu.dto.CreateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing categories.
 * Provides methods for creating, retrieving, updating, and deleting categories,
 * as well as retrieving paginated lists of categories.
 */
public interface CategoryService {

    /**
     * Retrieves a paginated list of all categories.
     *
     * @param pageable the pagination information
     * @return a paginated {@link Page} of {@link CategoryDto} representing all categories
     */
    Page<CategoryDto> findAll(Pageable pageable);

    /**
     * Retrieves the details of a specific category by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return a {@link CategoryDto} containing the details of the found category,
     * or null if not found
     */
    CategoryDto getById(Long id);

    /**
     * Saves a new category entity based on the provided details.
     *
     * @param createCategoryRequestDto a {@link CreateCategoryRequestDto}
     * containing the details of the category to save
     * @return the saved category entity as a {@link CategoryDto}
     */
    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto);

    /**
     * Updates an existing category with new details.
     *
     * @param id the unique identifier of the category to update
     * @param createCategoryRequestDto a {@link CreateCategoryRequestDto}
     * containing the new details of the category
     * @return an updated {@link CategoryDto} representing the updated category
     */
    CategoryDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto);

    /**
     * Deletes a category entity by its unique identifier.
     *
     * @param id the unique identifier of the category to delete
     */
    void deleteById(Long id);
}
