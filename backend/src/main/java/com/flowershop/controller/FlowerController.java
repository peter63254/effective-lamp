package com.flowershop.controller;

import com.flowershop.model.entity.Flower;
import com.flowershop.service.FlowerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<Flower> getAllFlowers(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword) {
        return flowerService.getAllFlowers(categoryId, keyword);
    }

    @GetMapping("/{id}")
    public Flower getFlowerById(@PathVariable Long id) {
        return flowerService.getFlowerById(id);
    }
}
