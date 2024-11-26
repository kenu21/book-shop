package com.keniu.controllers;

import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.models.User;
import com.keniu.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing cart-related operations in the book shop application.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@Tag(name = "Cart management", description = "Endpoints for managing cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    /**
     * Retrieves the shopping cart of the authenticated user.
     *
     * @param user the authenticated user
     * @return a {@link ShoppingCartDto} representing the user's shopping cart
     */
    @Operation(summary = "Retrieve user's shopping cart")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto getShoppingCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getShoppingCart(user.getEmail());
    }

    /**
     * Adds a new item to the authenticated user's shopping cart.
     *
     * @param user the authenticated user
     * @param createCartItemRequestDto the details of the item to add to the cart
     * @return an updated {@link ShoppingCartDto} representing the shopping cart
     */
    @Operation(summary = "Add an item to the shopping cart")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto addCartItem(@AuthenticationPrincipal User user,
            @Valid @RequestBody CreateCartItemRequestDto createCartItemRequestDto) {
        return shoppingCartService.addCartItem(user.getEmail(), createCartItemRequestDto);
    }

    /**
     * Updates the quantity of a specific item in the user's shopping cart.
     *
     * @param user the authenticated user
     * @param id the unique identifier of the cart item
     * @param updateCarItemRequestDto the updated quantity of the cart item
     * @return an updated {@link ShoppingCartDto} representing the shopping cart
     */
    @Operation(summary = "Update the quantity of an item in the shopping cart")
    @PutMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto updateCartItem(@AuthenticationPrincipal User user,
            @PathVariable Long id,
            @Valid @RequestBody UpdateCarItemRequestDto updateCarItemRequestDto) {
        return shoppingCartService.update(user.getEmail(), id, updateCarItemRequestDto);
    }

    /**
     * Removes a specific item from the user's shopping cart.
     *
     * @param user the authenticated user
     * @param id the unique identifier of the cart item to be removed
     * @return an updated {@link ShoppingCartDto} representing the shopping cart
     */
    @Operation(summary = "Remove an item from the shopping cart")
    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto deleteCartItem(@AuthenticationPrincipal User user,
            @PathVariable Long id) {
        return shoppingCartService.delete(user.getEmail(), id);
    }
}
