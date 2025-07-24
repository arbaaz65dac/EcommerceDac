package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.RatingDto;
import com.cdac.e_Commerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> submitRating(@RequestBody RatingDto ratingDto) {
        RatingDto saved = ratingService.submitRating(ratingDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<RatingDto>> getRatingsForProduct(@PathVariable Long productId) {
        List<RatingDto> ratings = ratingService.getRatingsForProduct(productId);
        return ResponseEntity.ok(ratings);
    }
} 