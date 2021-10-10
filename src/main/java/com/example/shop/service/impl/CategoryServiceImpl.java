package com.example.shop.service.impl;

import com.example.shop.entity.Category;
import com.example.shop.exception.CategoryNotFound;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String name) {
        if (name == null) {
            throw new CategoryNotFound("failed to add category");
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFound("Category not found by id"));
    }
}
