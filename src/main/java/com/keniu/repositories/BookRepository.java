package com.keniu.repositories;

import com.keniu.models.Book;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Book} entities.
 */
public interface BookRepository {

    /**
     * Saves a new or existing book entity.
     *
     * @param book the book entity to save
     * @return the saved book entity
     */
    Book save(Book book);

    /**
     * Retrieves all book entities from the repository.
     *
     * @return a list of all book entities
     */
    List<Book> findAll();

    /**
     * Retrieves a book entity by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return an {@link Optional} containing the found book entity,
     * or an empty {@code Optional} if not found
     */
    Optional<Book> findById(Long id);
}
