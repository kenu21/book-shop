package com.keniu.services;

import com.keniu.dtos.BookDto;
import com.keniu.dtos.CreateBookRequestDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.BookMapper;
import com.keniu.repositories.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(createBookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
            .map(bookMapper::toDto)
            .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + id)));
    }
}
