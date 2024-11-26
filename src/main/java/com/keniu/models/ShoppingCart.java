package com.keniu.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * Represents a shopping cart in the book shop application.
 * Each shopping cart is associated with a single {@link User}
 * and contains multiple {@link CartItem} entries.
 */
@Entity
@SQLDelete(sql = "UPDATE shopping_carts SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@Table(name = "shopping_carts")
public class ShoppingCart {

    /** The unique identifier for the shopping cart. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who owns this shopping cart.
     * This is a one-to-one relationship with the {@link User} entity.
     */
    @JoinColumn(nullable = false)
    @OneToOne
    private User user;

    /**
     * A collection of cart items that belong to this shopping cart.
     * Each cart item represents a specific {@link Book} and its quantity.
     * This is a one-to-many relationship with cascading enabled.
     */
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    /**
     * Indicates if the shopping cart is marked as deleted (soft delete).
     * Used to implement logical deletion instead of physically removing the cart.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
}
