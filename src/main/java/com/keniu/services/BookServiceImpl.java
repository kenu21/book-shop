package com.keniu.services;

import com.keniu.dto.BookDto;
import com.keniu.dto.CreateBookRequestDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.BookMapper;
import com.keniu.models.Book;
import com.keniu.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<BookDto> findAll(String email, Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
    }

    @Override
    public BookDto getById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + id)));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
