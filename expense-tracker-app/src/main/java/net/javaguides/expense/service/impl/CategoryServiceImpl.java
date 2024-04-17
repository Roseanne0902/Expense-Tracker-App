package net.javaguides.expense.service.impl;

import net.javaguides.expense.controller.CategoryController;
import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.exceptions.ResourceNotFoundException;
import net.javaguides.expense.mapper.CategoryMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("The category does not exist ưith id" + id));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
         List<Category> categories = categoryRepository.findAll();
         return categories.stream().map(category->CategoryMapper.mapToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("cannot update this category"));
        category.setName(categoryDto.name());
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("cannot find the category"));
        categoryRepository.deleteById(categoryId);
    }

}
