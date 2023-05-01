package com.example.blogapp.services.impl;

import com.example.blogapp.entities.Category;
import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.repositories.CategoryRepo;
import com.example.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category savedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDesc(categoryDto.getCategoryDesc());

        Category updateCat = this.categoryRepo.save(category);

        return this.modelMapper.map(updateCat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> allCat = this.categoryRepo.findAll();
        return allCat.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
