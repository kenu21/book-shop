package com.keniu.services;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.OrderMapper;
import com.keniu.models.CartItem;
import com.keniu.models.Order;
import com.keniu.models.OrderItem;
import com.keniu.models.ShoppingCart;
import com.keniu.models.Status;
import com.keniu.models.User;
import com.keniu.repositories.OrderRepository;
import com.keniu.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public OrderDto save(
            User user,
            CreateOrderRequestDto createOrderRequestDto) {
        Order order = orderMapper.toModel(createOrderRequestDto);
        order.setUser(user);
        order.setStatus(Status.PENDING);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Can't find cart for user with id " + user.getId())
                );
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        BigDecimal total = BigDecimal.ZERO;
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            BigDecimal price = cartItem.getBook().getPrice();
            orderItem.setPrice(price);
            orderItems.add(orderItem);
            total = total.add(price.multiply(new BigDecimal(cartItem.getQuantity())));
        }
        order.setOrderItems(orderItems);
        order.setTotal(total);
        order.setOrderDate(LocalDateTime.now());
        return orderMapper.toDto(orderRepository.save(order));
    }
}
