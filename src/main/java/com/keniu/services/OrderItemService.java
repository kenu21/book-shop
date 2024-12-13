package com.keniu.services;

import com.keniu.dto.OrderItemDto;
import java.util.Set;

public interface OrderItemService {

    Set<OrderItemDto> findItemsForOrder(Long orderId);

    OrderItemDto findItemForOrder(Long orderId, Long itemId);
}
