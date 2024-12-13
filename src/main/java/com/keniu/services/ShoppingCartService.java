package com.keniu.services;

import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.models.ShoppingCart;

public interface ShoppingCartService {
    
    ShoppingCartDto save(ShoppingCart shoppingCart);
    
    ShoppingCartDto getShoppingCart(Long userId);
    
    ShoppingCartDto addCartItem(Long userId, CreateCartItemRequestDto createCartItemRequestDto);
    
    ShoppingCartDto update(Long userId, Long id, UpdateCarItemRequestDto updateCarItemRequestDto);
    
    ShoppingCartDto delete(Long userId, Long id);
}
