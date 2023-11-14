package com.example.forummanagementsystem.repository.contracts;

import com.example.forummanagementsystem.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
    Category getById(int id);
}
