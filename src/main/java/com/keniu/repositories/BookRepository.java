package com.keniu.repositories;

import com.keniu.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByCategories_Id(Long categoryId, Pageable pageable);
}
