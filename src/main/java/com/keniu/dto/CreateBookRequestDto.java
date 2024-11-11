package com.keniu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for creating
 * a new book in the book shop application.
 * Contains necessary information to create a book entity.
 */
@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    @NotNull
    private BigDecimal price;
    private String description;
    private String coverImage;
}
