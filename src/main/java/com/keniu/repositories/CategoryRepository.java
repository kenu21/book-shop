package com.keniu.repositories;

import com.keniu.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations
 * on {@link Category} entities.
 * Extends {@link JpaRepository} to provide default implementations
 * for common database interactions.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
