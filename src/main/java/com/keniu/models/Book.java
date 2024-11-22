package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * Represents a book in the book shop application.
 */
@Entity
@SQLDelete(sql = "UPDATE book SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
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
    @Size(max = 255)
    @Column(nullable = false)
    private String title;

    /** The author of the book. */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String author;

    /** The ISBN of the book, which should be unique. */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String isbn;

    /** The price of the book. */
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    /** A brief description of the book. */
    private String description;

    /**
     * A set of categories that the book belongs to.
     * Each book can have multiple categories, such as genre, author-related categories, etc.
     */
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Category> categories;

    /** The URL of the book's cover image. */
    @Size(max = 255)
    private String coverImage;

    /** Indicates if the book is marked as deleted (soft delete). */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
}
