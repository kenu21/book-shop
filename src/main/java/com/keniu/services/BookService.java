package com.keniu.services;

import com.keniu.dto.BookDto;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.models.Book;
import java.util.List;

/**
 * Service interface for managing {@link Book} entities.
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
     * Retrieves all book entities from the service.
     *
     * @return a list of {@link BookDto} representing all books
     */
    List<BookDto> findAll();

    /**
     * Retrieves the details of a specific book by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return a {@link BookDto} containing the details of the found book, or null if not found
     */
    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);
}
