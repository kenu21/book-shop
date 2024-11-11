package com.keniu.controllers;

import com.keniu.dto.BookDto;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for managing book-related operations in the book shop application.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@Tag(name = "Book management", description = "Endpoints for managing books")
public class BookController {
    private final BookService bookService;

    /**
     * Retrieves a list of all books in the book shop.
     *
     * @return a list of {@link BookDto} objects representing all books
     */
    @Operation(summary = "Find all books")
    @GetMapping
    public List<BookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    /**
     * Retrieves the details of a specific book by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return a {@link BookDto} containing the details of the specified book
     */
    @Operation(summary = "Find book by id")
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    /**
     * Creates a new book in the book shop based on the provided details.
     *
     * @param createBookRequestDto a {@link CreateBookRequestDto}
     * containing details for the new book
     * @return a {@link BookDto} representing the created book
     */
    @Operation(summary = "Create book")
    @PostMapping
    public BookDto save(@Valid @RequestBody CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    /**
     * Updates the details of an existing book in the book shop.
     *
     * This method accepts a book ID and the new details of the book.
     * It updates the existing book in the database
     * with the provided information.
     *
     * @param id the unique identifier of the book to be updated
     * @param createBookRequestDto
     * a {@link CreateBookRequestDto} containing the new details of the book
     * @return a {@link BookDto} representing the updated book with the new details
     */
    @Operation(summary = "Update or crate book")
    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id,
            @ Valid @RequestBody CreateBookRequestDto createBookRequestDto) {
        return bookService.update(id, createBookRequestDto);
    }

    /**
     * Deletes a book from the book shop by its unique identifier.
     *
     * @param id the unique identifier of the book to be deleted
     */
    @Operation(summary = "Delete book by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
