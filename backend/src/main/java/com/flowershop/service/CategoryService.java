package com.flowershop.service;

import com.flowershop.model.dto.ApiResponse;
import com.flowershop.model.entity.Category;
import com.flowershop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc();
    }

    public ApiResponse<Category> createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            return ApiResponse.error("Category name already exists");
        }
        Category saved = categoryRepository.save(category);
        return ApiResponse.success("Category created", saved);
    }

    public ApiResponse<Category> updateCategory(Long id, Category category) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            existing.setSortOrder(category.getSortOrder());
            Category saved = categoryRepository.save(existing);
            return ApiResponse.success("Category updated", saved);
        }).orElse(ApiResponse.error("Category not found"));
    }

    public ApiResponse<Void> deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            return ApiResponse.error("Category not found");
        }
        categoryRepository.deleteById(id);
        return ApiResponse.success("Category deleted");
    }
}
