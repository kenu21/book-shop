package com.keniu.services;

import com.keniu.dto.BookDto;
import com.keniu.dto.BookDtoWithoutCategoryIds;
import com.keniu.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(CreateBookRequestDto createBookRequestDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);
    
    void deleteById(Long id);
    
    Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable);
}
