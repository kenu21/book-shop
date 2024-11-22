package com.keniu.services;

import com.keniu.dto.BookDto;
import com.keniu.dto.BookDtoWithoutCategoryIds;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing {@link Book} entities.
 * Provides methods for creating, retrieving, updating, and deleting books,
 * as well as specific queries related to book categories.
 */
public interface BookService {

    /**
     * Saves a new book entity based on the provided details.
     *
     * @param createBookRequestDto a {@link CreateBookRequestDto}
     * containing the details of the book to save
     * @return the saved book entity as a {@link BookDto}
     */
    BookDto save(CreateBookRequestDto createBookRequestDto);

    /**
     * Retrieves a paginated list of all book entities from the service.
     *
     * @param pageable the pagination information
     * @return a paginated {@link Page} of {@link BookDto} representing all books
     */
    Page<BookDto> findAll(Pageable pageable);

    /**
     * Retrieves the details of a specific book by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return a {@link BookDto} containing the details of the found book, or null if not found
     */
    BookDto getById(Long id);

    /**
     * Updates an existing book entity with new details.
     *
     * @param id the unique identifier of the book to be updated
     * @param createBookRequestDto a {@link CreateBookRequestDto}
     * containing the new details of the book
     * @return an updated {@link BookDto} representing the updated book
     */
    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);

    /**
     * Deletes a book entity by its unique identifier.
     *
     * @param id the unique identifier of the book to be deleted
     */
    void deleteById(Long id);

    /**
     * Retrieves a paginated list of books that belong to a specific category.
     *
     * @param id the unique identifier of the category
     * @param pageable the pagination information
     * @return a paginated {@link Page} of {@link BookDtoWithoutCategoryIds}
     * representing books in the specified category
     */
    Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable);
}
