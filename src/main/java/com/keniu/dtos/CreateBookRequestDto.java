package com.keniu.dtos;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for creating
 * a new book in the book shop application.
 * Contains necessary information to create a book entity.
 */
@Data
public class CreateBookRequestDto {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
