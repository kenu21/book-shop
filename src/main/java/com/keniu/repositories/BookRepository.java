package com.keniu.repositories;

import com.keniu.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
