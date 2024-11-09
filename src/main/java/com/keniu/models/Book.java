package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;

/**
 * Represents a book in the book shop application.
 */
@Entity
@SQLDelete(sql = "UPDATE book SET is_deleted = true WHERE id=?")
@Filter(name = "softDeleteFilter", condition = "is_deleted = false")
@Getter
@Setter
@Table(name = "books")
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
    @Column(nullable = false, unique = true)
    private String isbn;

    /** The price of the book. */
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    /** A brief description of the book. */
    private String description;

    /** The URL of the book's cover image. */
    private String coverImage;

    /** Indicates if the book is marked as deleted (soft delete). */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
}
