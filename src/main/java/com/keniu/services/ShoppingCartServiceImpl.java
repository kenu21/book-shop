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
    public ShoppingCartDto getShoppingCart(String email) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(email);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addCartItem(String email,
            CreateCartItemRequestDto createCartItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(email);
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
    public ShoppingCartDto update(String email, Long id,
            UpdateCarItemRequestDto updateCarItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(email);
        CartItem cartItem = getCartItemById(shoppingCart, id);
        cartItem.setQuantity(updateCarItemRequestDto.getQuantity());
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto delete(String email, Long id) {
        ShoppingCart shoppingCart = getShoppingCartByEmail(email);
        CartItem cartItem = getCartItemById(shoppingCart, id);
        cartItem.setIsDeleted(true);
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private ShoppingCart getShoppingCartByEmail(String email) {
        return shoppingCartRepository.findByUserEmail(email)
            .orElseThrow(() ->
                new EntityNotFoundException("Can't find cart for user with email " + email));
    }

    private CartItem getCartItemById(ShoppingCart shoppingCart, Long id) {
        return shoppingCart.getCartItems().stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Can't find CartItem with id " + id));
    }
}
