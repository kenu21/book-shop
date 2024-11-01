package com.keniu.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String title;

    /** The author of the book. */
    @NotNull
    private String author;

    /** The ISBN of the book, which should be unique. */
    @NotNull
    // todo add unique annotation
    private String isbn;

    /** The price of the book. */
    @NotNull
    private BigDecimal price;

    /** A brief description of the book. */
    private String description;

    /** The URL of the book's cover image. */
    private String coverImage;

}
