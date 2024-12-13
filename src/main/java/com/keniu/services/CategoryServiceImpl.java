package com.keniu.services;

import com.keniu.dto.CategoryDto;
import com.keniu.dto.CreateCategoryRequestDto;
import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.mappers.CategoryMapper;
import com.keniu.models.Category;
import com.keniu.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Can't find category by id " + id)));
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryMapper.toDto(categoryRepository.save(
            categoryMapper.toModel(createCategoryRequestDto))
        );
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Can't find category by id " + id));
        categoryMapper.updateCategoryFromDto(createCategoryRequestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
