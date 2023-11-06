package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.models.Category;
import com.example.forummanagementsystem.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CategoryServiceTests {
    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        Category mockCategory = Helpers.createMockCategory();
        when(categoryRepository.getById(1)).thenReturn(mockCategory);

        // Act
        Category category = categoryService.getById(1);

        // Assert
        assertEquals(mockCategory.getId(), category.getId());
        assertEquals(mockCategory.getName(), category.getName());
    }

    @Test
    public void testGetAllCategories_ShouldCallRepository() {
        // Act
        categoryService.getAll();

        // Assert
        Mockito.verify(categoryRepository, times(1)).getAll();
    }

}
