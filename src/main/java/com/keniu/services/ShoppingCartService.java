package com.keniu.services;

import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.models.ShoppingCart;

public interface ShoppingCartService {
    
    ShoppingCart save(ShoppingCart shoppingCart);
    
    ShoppingCartDto getShoppingCart(String email);
    
    ShoppingCartDto addCartItem(String email, CreateCartItemRequestDto createCartItemRequestDto);
    
    ShoppingCartDto update(String email, Long id, UpdateCarItemRequestDto updateCarItemRequestDto);
    
    ShoppingCartDto delete(String email, Long id);
}
