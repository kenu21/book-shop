package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.BookDto;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.models.Book;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between
 * {@link Book} entities and their corresponding DTOs.
 */
@Mapper(config = MapperConfig.class)
public interface BookMapper {

    /**
     * Converts a {@link Book} entity to a {@link BookDto}.
     *
     * @param book the book entity to convert
     * @return the corresponding BookDto
     */
    BookDto toDto(Book book);

    /**
     * Converts a {@link CreateBookRequestDto} to a {@link Book} entity.
     *
     * @param createBookRequestDto the DTO containing data to create a new book
     * @return the newly created Book entity
     */
    Book toModel(CreateBookRequestDto createBookRequestDto);
}
