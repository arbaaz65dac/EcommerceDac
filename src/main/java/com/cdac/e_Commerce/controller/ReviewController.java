package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.ReviewDto;
import com.cdac.e_Commerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> submitReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto saved = reviewService.submitReview(reviewDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsForProduct(@PathVariable Long productId) {
        List<ReviewDto> reviews = reviewService.getReviewsForProduct(productId);
        return ResponseEntity.ok(reviews);
    }
} 