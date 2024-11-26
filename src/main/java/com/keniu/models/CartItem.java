package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * Represents an item in the shopping cart of a user.
 * Each cart item is linked to a specific {@link ShoppingCart} and a specific {@link Book}.
 */
@Entity
@SQLDelete(sql = "UPDATE cart_items SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@Table(name = "cart_items")
public class CartItem {

    /** The unique identifier for the cart item. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The shopping cart that this item belongs to.
     * This relationship establishes a many-to-one connection with {@link ShoppingCart}.
     */
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    /**
     * The book associated with this cart item.
     * This relationship establishes a many-to-one connection with {@link Book}.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    /**
     * The quantity of the book added to the shopping cart.
     * Specifies how many copies of the book the user intends to purchase.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Indicates if the cart item is marked as deleted (soft delete).
     * This is used to implement logical deletion instead of removing the item from the database.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
}
