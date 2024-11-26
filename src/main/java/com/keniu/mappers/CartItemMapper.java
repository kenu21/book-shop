package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CartItemDto;
import com.keniu.models.Book;
import com.keniu.models.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper interface for converting between
 * {@link CartItem} entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic generation of implementation.
 */
@Mapper(config = MapperConfig.class)
public interface CartItemMapper {

    /**
     * Converts a {@link CartItem} entity to a {@link CartItemDto}.
     *
     * @param cartItem the cart item entity to convert
     * @return the corresponding CartItemDto
     */
    @Mapping(target = "bookId", source = "book",
            qualifiedByName = "mapBookToBookId")
    @Mapping(target = "bookTitle", source = "book",
            qualifiedByName = "mapBookToBookTitle")
    CartItemDto toDto(CartItem cartItem);

    /**
     * Maps a {@link Book} entity to its ID.
     * Used in mapping CartItem entities to DTOs.
     *
     * @param book the book entity
     * @return the ID of the book
     */
    @Named("mapBookToBookId")
    default Long mapBookToBookId(Book book) {
        return book.getId();
    }

    /**
     * Maps a {@link Book} entity to its title.
     * Used in mapping CartItem entities to DTOs.
     *
     * @param book the book entity
     * @return the title of the book
     */
    @Named("mapBookToBookTitle")
    default String mapBookToBookTitle(Book book) {
        return book.getTitle();
    }
}
