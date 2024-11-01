package com.keniu.repositories;

import com.keniu.models.Book;
import java.util.List;

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
}
