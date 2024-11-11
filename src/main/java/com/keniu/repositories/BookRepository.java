package com.keniu.repositories;

import com.keniu.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for managing {@link Book} entities.
 */
public interface BookRepository extends JpaRepository<Book, Long>,
        PagingAndSortingRepository<Book, Long> {

}
