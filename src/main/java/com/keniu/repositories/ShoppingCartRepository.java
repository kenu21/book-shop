package com.keniu.repositories;

import com.keniu.models.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link ShoppingCart} entities.
 * Provides methods for interacting with the database table
 * associated with the `ShoppingCart` entity.
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    /**
     * Finds a shopping cart by the email address of its associated user.
     *
     * @param email the email address of the user
     * @return an {@link Optional} containing the found shopping cart, or empty if none found
     */
    Optional<ShoppingCart> findByUserEmail(String email);
}
