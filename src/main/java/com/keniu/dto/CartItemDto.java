package com.keniu.dto;

import lombok.Data;

@Data
public class CartItemDto {

    private Long id;
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
}
