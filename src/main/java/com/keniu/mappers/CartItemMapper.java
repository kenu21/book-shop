package com.keniu.mappers;

import com.keniu.config.MapperConfig;
import com.keniu.dto.CartItemDto;
import com.keniu.models.Book;
import com.keniu.models.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {

    @Mapping(target = "bookId", source = "book",
            qualifiedByName = "mapBookToBookId")
    @Mapping(target = "bookTitle", source = "book",
            qualifiedByName = "mapBookToBookTitle")
    CartItemDto toDto(CartItem cartItem);

    @Named("mapBookToBookId")
    default Long mapBookToBookId(Book book) {
        return book.getId();
    }

    @Named("mapBookToBookTitle")
    default String mapBookToBookTitle(Book book) {
        return book.getTitle();
    }
}
