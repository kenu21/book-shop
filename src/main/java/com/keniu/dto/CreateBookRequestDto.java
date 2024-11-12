package com.keniu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255)
    private String title;
    @NotBlank
    @Size(max = 255)
    private String author;
    @NotBlank
    @Size(max = 255)
    private String isbn;
    @NotNull
    private BigDecimal price;
    private String description;
    @Size(max = 255)
    private String coverImage;
}
