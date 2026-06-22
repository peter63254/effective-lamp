package com.flowershop.controller;

import com.flowershop.model.dto.ApiResponse;
import com.flowershop.model.entity.Category;
import com.flowershop.model.entity.Flower;
import com.flowershop.model.entity.User;
import com.flowershop.repository.UserRepository;
import com.flowershop.service.CategoryService;
import com.flowershop.service.FlowerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final FlowerService flowerService;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public AdminController(FlowerService flowerService, CategoryService categoryService,
                           UserRepository userRepository) {
        this.flowerService = flowerService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    // --- Flower Management ---
    @PostMapping("/flowers")
    public ApiResponse<Flower> createFlower(@Valid @RequestBody Flower flower,
                                             @RequestParam(required = false) Long categoryId) {
        return flowerService.createFlower(flower, categoryId);
    }

    @PutMapping("/flowers/{id}")
    public ApiResponse<Flower> updateFlower(@PathVariable Long id,
                                             @Valid @RequestBody Flower flower,
                                             @RequestParam(required = false) Long categoryId) {
        return flowerService.updateFlower(id, flower, categoryId);
    }

    @PatchMapping("/flowers/{id}/stock")
    public ApiResponse<Flower> updateStockStatus(@PathVariable Long id, @RequestParam String status) {
        return flowerService.updateStockStatus(id, status);
    }

    @DeleteMapping("/flowers/{id}")
    public ApiResponse<Void> deleteFlower(@PathVariable Long id) {
        return flowerService.deleteFlower(id);
    }

    // --- Category Management ---
    @PostMapping("/categories")
    public ApiResponse<Category> createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/categories/{id}")
    public ApiResponse<Category> updateCategory(@PathVariable Long id,
                                                 @Valid @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    // --- User Management ---
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

}
