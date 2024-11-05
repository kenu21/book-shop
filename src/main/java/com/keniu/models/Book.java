package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * Represents a book in the book shop application.
 */
@Entity
public class Book {

    /** The unique identifier for the book. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The title of the book. */
    @NotBlank
    @Column(nullable = false)
    private String title;

    /** The author of the book. */
    @NotBlank
    @Column(nullable = false)
    private String author;

    /** The ISBN of the book, which should be unique. */
    @NotBlank
    @Column(nullable = false)
    // todo add unique annotation
    private String isbn;

    /** The price of the book. */
    @NotBlank
    @Column(nullable = false)
    private BigDecimal price;

    /** A brief description of the book. */
    private String description;

    /** The URL of the book's cover image. */
    private String coverImage;

}
