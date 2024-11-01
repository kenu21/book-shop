package com.keniu.services;

import com.keniu.models.Book;
import java.util.List;

/**
 * Service interface for managing {@link Book} entities.
 */
public interface BookService {

    /**
     * Saves a new or existing book entity.
     *
     * @param book the book entity to save
     * @return the saved book entity
     */
    Book save(Book book);

    /**
     * Retrieves all book entities from the service.
     *
     * @return a list of all book entities
     */
    List<Book> findAll();
}
