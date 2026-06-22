package com.flowershop.service;

import com.flowershop.model.dto.ApiResponse;
import com.flowershop.model.entity.Category;
import com.flowershop.model.entity.Flower;
import com.flowershop.repository.CategoryRepository;
import com.flowershop.repository.FlowerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlowerService {

    private final FlowerRepository flowerRepository;
    private final CategoryRepository categoryRepository;

    public FlowerService(FlowerRepository flowerRepository, CategoryRepository categoryRepository) {
        this.flowerRepository = flowerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Flower> getAllFlowers(Long categoryId, String keyword) {
        if (categoryId != null || (keyword != null && !keyword.isEmpty())) {
            return flowerRepository.searchFlowers(categoryId, keyword);
        }
        return flowerRepository.findAllByOrderByCreatedAtDesc();
    }

    public Flower getFlowerById(Long id) {
        return flowerRepository.findById(id).orElse(null);
    }

    public ApiResponse<Flower> createFlower(Flower flower, Long categoryId) {
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category == null) {
                return ApiResponse.error("Category not found");
            }
            flower.setCategory(category);
        }
        if (flower.getPrice() == null) {
            flower.setPrice(BigDecimal.ZERO);
        }
        if (flower.getStockStatus() == null) {
            flower.setStockStatus("IN_STOCK");
        }
        Flower saved = flowerRepository.save(flower);
        return ApiResponse.success("Flower created", saved);
    }

    public ApiResponse<Flower> updateFlower(Long id, Flower flower, Long categoryId) {
        return flowerRepository.findById(id).map(existing -> {
            existing.setName(flower.getName());
            existing.setDescription(flower.getDescription());
            existing.setPrice(flower.getPrice());
            existing.setImageUrl(flower.getImageUrl());
            existing.setColor(flower.getColor());
            existing.setLanguage(flower.getLanguage());
            if (flower.getStockStatus() != null) {
                existing.setStockStatus(flower.getStockStatus());
            }
            if (categoryId != null) {
                Category category = categoryRepository.findById(categoryId).orElse(null);
                if (category != null) {
                    existing.setCategory(category);
                }
            }
            Flower saved = flowerRepository.save(existing);
            return ApiResponse.success("Flower updated", saved);
        }).orElse(ApiResponse.error("Flower not found"));
    }

    public ApiResponse<Flower> updateStockStatus(Long id, String status) {
        return flowerRepository.findById(id).map(existing -> {
            existing.setStockStatus(status);
            Flower saved = flowerRepository.save(existing);
            return ApiResponse.success("Stock status updated", saved);
        }).orElse(ApiResponse.error("Flower not found"));
    }

    public ApiResponse<Void> deleteFlower(Long id) {
        if (!flowerRepository.existsById(id)) {
            return ApiResponse.error("Flower not found");
        }
        flowerRepository.deleteById(id);
        return ApiResponse.success("Flower deleted");
    }
}
