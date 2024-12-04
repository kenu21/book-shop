package com.keniu.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private long id;
    private long bookId;
    private int quantity;
}
