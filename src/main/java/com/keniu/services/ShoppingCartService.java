package com.keniu.services;

import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.models.ShoppingCart;

/**
 * Service interface for managing shopping cart-related operations.
 */
public interface ShoppingCartService {

    /**
     * Saves a shopping cart entity.
     *
     * @param shoppingCart the {@link ShoppingCart} entity to save
     * @return the saved shopping cart as a {@link ShoppingCart}
     */
    ShoppingCart save(ShoppingCart shoppingCart);

    /**
     * Retrieves the shopping cart of a user based on their email address.
     *
     * @param email the email address of the user
     * @return the user's shopping cart as a {@link ShoppingCartDto}
     */
    ShoppingCartDto getShoppingCart(String email);

    /**
     * Adds an item to the shopping cart of a user.
     *
     * @param email the email address of the user
     * @param createCartItemRequestDto the {@link CreateCartItemRequestDto} containing item details
     * @return the updated shopping cart as a {@link ShoppingCartDto}
     */
    ShoppingCartDto addCartItem(String email, CreateCartItemRequestDto createCartItemRequestDto);

    /**
     * Updates an existing item in the shopping cart of a user.
     *
     * @param email the email address of the user
     * @param id the unique identifier of the cart item to update
     * @param updateCarItemRequestDto the {
     * @link UpdateCarItemRequestDto
     * } containing updated item details
     * @return the updated shopping cart as a {@link ShoppingCartDto}
     */
    ShoppingCartDto update(String email, Long id, UpdateCarItemRequestDto updateCarItemRequestDto);

    /**
     * Deletes an item from the shopping cart of a user.
     *
     * @param email the email address of the user
     * @param id the unique identifier of the cart item to delete
     * @return the updated shopping cart as a {@link ShoppingCartDto}
     */
    ShoppingCartDto delete(String email, Long id);
}
