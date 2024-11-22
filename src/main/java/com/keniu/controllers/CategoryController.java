package com.keniu.controllers;

import com.keniu.dto.BookDtoWithoutCategoryIds;
import com.keniu.dto.CategoryDto;
import com.keniu.dto.CreateCategoryRequestDto;
import com.keniu.models.Book;
import com.keniu.services.BookService;
import com.keniu.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing category-related operations in the book shop application.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@Tag(name = "Category management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    /**
     * Creates a new category in the system.
     *
     * @param createCategoryRequestDto the details of the category to be created
     * @return the created {@link CategoryDto}
     */
    @Operation(summary = "Create a new category")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto createCategory(
            @Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDto
    ) {
        return categoryService.save(createCategoryRequestDto);
    }

    /**
     * Retrieves a list of all categories.
     *
     * @param pageable pagination information
     * @return a {@link Page} of {@link CategoryDto}
     */
    @Operation(summary = "Retrieve all categories")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public Page<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param id the ID of the category
     * @return the {@link CategoryDto} with the given ID
     */
    @Operation(summary = "Retrieve a category by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to be updated
     * @param createCategoryRequestDto the new details of the category
     * @return the updated {@link CategoryDto}
     */
    @Operation(summary = "Update or create a category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.update(id, createCategoryRequestDto);
    }

    /**
     * Deletes a specific category by its ID.
     *
     * @param id the ID of the category to be deleted
     */
    @Operation(summary = "Delete a category by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    /**
     * Retrieves books associated with a specific category.
     *
     * @param id the ID of the category
     * @param pageable pagination information
     * @return a {@link Page} of {@link Book} associated with the given category ID
     */
    @Operation(summary = "Retrieve books by category ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}/books")
    public Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable Long id, Pageable pageable) {
        return bookService.getBooksByCategoryId(id, pageable);
    }
}
