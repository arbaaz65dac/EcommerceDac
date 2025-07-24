package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.ReviewDto;
import java.util.List;

public interface ReviewService {
    ReviewDto submitReview(ReviewDto reviewDto);
    List<ReviewDto> getReviewsForProduct(Long productId);
} 