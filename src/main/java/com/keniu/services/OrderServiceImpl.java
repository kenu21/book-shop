package com.keniu.services;

import com.keniu.dto.CreateOrderRequestDto;
import com.keniu.dto.OrderDto;
import com.keniu.dto.UpdateOrderRequestDto;
import com.keniu.exceptions.EmptyShoppingCartException;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.OrderItemMapper;
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
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public OrderDto save(User user, CreateOrderRequestDto createOrderRequestDto) {
        Order order = createOrderFromDto(createOrderRequestDto, user);
        OrderDto orderDto = orderMapper.toDto(orderRepository.save(order));
        shoppingCartService.clean(user.getId());
        return orderDto;
    }

    @Override
    public Page<OrderDto> findAll(User user, Pageable pageable) {
        return orderRepository.findByUserId(user.getId(), pageable)
            .map(orderMapper::toDto);
    }

    @Override
    public OrderDto update(Long id, UpdateOrderRequestDto updateOrderRequestDto) {
        Order order = findOrderById(id);
        orderMapper.updateOrderFromDto(updateOrderRequestDto, order);
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order createOrderFromDto(CreateOrderRequestDto createOrderRequestDto, User user) {
        Order order = orderMapper.toModel(createOrderRequestDto);
        order.setUser(user);
        order.setStatus(Status.PENDING);
        ShoppingCart shoppingCart = findShoppingCartByUser(user);
        Set<OrderItem> orderItems = createOrderItems(shoppingCart, order);
        BigDecimal total = calculateTotal(orderItems);
        order.setOrderItems(orderItems);
        order.setTotal(total);
        return order;
    }

    private ShoppingCart findShoppingCartByUser(User user) {
        return shoppingCartRepository.findByUserId(user.getId())
            .orElseThrow(() ->
                new EntityNotFoundException("Can't find cart for user with id " + user.getId()));
    }

    private Set<OrderItem> createOrderItems(ShoppingCart shoppingCart, Order order) {
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new EmptyShoppingCartException("Your cart is empty!");
        }
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private BigDecimal calculateTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
            .map(orderItem ->
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Can't find order by id " + id));
    }
}
