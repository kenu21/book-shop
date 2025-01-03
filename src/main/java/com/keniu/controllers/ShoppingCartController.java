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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@Tag(
        name = "Cart Management",
        description = "This section provides endpoints for managing a user's shopping cart. "
        + "You can retrieve the cart, add items, update quantities, or remove items. "
        + "Access is controlled based on user roles. Admins and users can manage their own carts."
)
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Retrieve user's shopping cart")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto getShoppingCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @Operation(summary = "Add an item to the shopping cart")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartDto addCartItem(@AuthenticationPrincipal User user,
            @Valid @RequestBody CreateCartItemRequestDto createCartItemRequestDto) {
        return shoppingCartService.addCartItem(user.getId(), createCartItemRequestDto);
    }

    @Operation(summary = "Update the quantity of an item in the shopping cart")
    @PutMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto updateCartItem(@AuthenticationPrincipal User user,
            @PathVariable Long id,
            @Valid @RequestBody UpdateCarItemRequestDto updateCarItemRequestDto) {
        return shoppingCartService.update(user.getId(), id, updateCarItemRequestDto);
    }

    @Operation(summary = "Remove an item from the shopping cart")
    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto deleteCartItem(@AuthenticationPrincipal User user,
            @PathVariable Long id) {
        return shoppingCartService.delete(user.getId(), id);
    }
}
