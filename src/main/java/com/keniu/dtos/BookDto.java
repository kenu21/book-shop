package com.keniu.dtos;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for
 * representing a book in the book shop application.
 * This class is used to transfer book data between the client and the server.
 */
@Data
public class BookDto {
    private Long id;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
