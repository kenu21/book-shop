package com.keniu.services;

import com.keniu.dto.CreateCartItemRequestDto;
import com.keniu.dto.ShoppingCartDto;
import com.keniu.dto.UpdateCarItemRequestDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.ShoppingCartMapper;
import com.keniu.models.Book;
import com.keniu.models.CartItem;
import com.keniu.models.ShoppingCart;
import com.keniu.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long userId) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(userId);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addCartItem(Long userId,
            CreateCartItemRequestDto createCartItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(userId);
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        Book book = new Book();
        book.setId(createCartItemRequestDto.getBookId());
        cartItem.setBook(book);
        cartItem.setQuantity(createCartItemRequestDto.getQuantity());
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto update(Long userId, Long id,
            UpdateCarItemRequestDto updateCarItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(userId);
        CartItem cartItem = getCartItemById(shoppingCart, id);
        cartItem.setQuantity(updateCarItemRequestDto.getQuantity());
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto delete(Long userId, Long id) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(userId);
        CartItem cartItem = getCartItemById(shoppingCart, id);
        cartItem.setIsDeleted(true);
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private ShoppingCart getShoppingCartByEmail(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
            .orElseThrow(() ->
                new EntityNotFoundException("Can't find cart for user with id " + userId));
    }

    private CartItem getCartItemById(ShoppingCart shoppingCart, Long cartItemId) {
        return shoppingCart.getCartItems().stream()
            .filter(item -> item.getId().equals(cartItemId))
            .findFirst()
            .orElseThrow(() ->
                new EntityNotFoundException("Can't find CartItem with id " + cartItemId));
    }
}
