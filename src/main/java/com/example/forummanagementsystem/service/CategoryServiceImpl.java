package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Category;
import com.example.forummanagementsystem.repository.contracts.CategoryRepository;
import com.example.forummanagementsystem.service.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.getById(id);
    }
}
