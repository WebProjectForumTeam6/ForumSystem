package com.example.forummanagementsystem.service.contracts;

import com.example.forummanagementsystem.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int id);
}
