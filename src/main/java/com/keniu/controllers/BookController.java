package com.keniu.controllers;

import com.keniu.dtos.BookDto;
import com.keniu.dtos.CreateBookRequestDto;
import com.keniu.services.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing book-related operations in the book shop application.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    /**
     * Retrieves a list of all books in the book shop.
     *
     * @return a list of {@link BookDto} objects representing all books
     */
    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAll();
    }

    /**
     * Retrieves the details of a specific book by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return a {@link BookDto} containing the details of the specified book
     */
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    /**
     * Creates a new book in the book shop based on the provided details.
     *
     * @param createBookRequestDto a {@link CreateBookRequestDto}
     * containing details for the new book
     * @return a {@link BookDto} representing the created book
     */
    @PostMapping
    public BookDto save(@RequestBody CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }
}
