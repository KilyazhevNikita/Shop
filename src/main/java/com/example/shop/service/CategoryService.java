package com.example.shop.service;

import com.example.shop.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(String name);

    List<Category> getAllCategory();

    Category getCategoryById(int id);
}
