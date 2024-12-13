package com.keniu.repositories;

import com.keniu.models.OrderItem;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> findAllByOrderId(Long orderId);

    Optional<OrderItem> findByOrder_idAndId(Long orderId, Long itemId);
}
