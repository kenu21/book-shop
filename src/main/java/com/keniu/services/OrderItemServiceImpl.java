package com.keniu.services;

import com.keniu.dto.OrderItemDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.OrderItemMapper;
import com.keniu.models.OrderItem;
import com.keniu.repositories.OrderItemRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public Set<OrderItemDto> findItemsForOrder(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream()
            .map(orderItemMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public OrderItemDto findItemForOrder(Long orderId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findByOrder_idAndId(orderId, itemId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find item with orderId"
                + orderId + "and with ItemId " + itemId));
        return orderItemMapper.toDto(orderItem);
    }
}
