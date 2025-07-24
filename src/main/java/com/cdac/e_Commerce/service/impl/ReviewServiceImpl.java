package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.ReviewService;
import com.cdac.e_Commerce.dto.ReviewDto;
import com.cdac.e_Commerce.model.Review;
import com.cdac.e_Commerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    private ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setProductId(review.getProductId());
        dto.setUserId(review.getUserId());
        dto.setContent(review.getContent());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }

    private Review toEntity(ReviewDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setProductId(dto.getProductId());
        review.setUserId(dto.getUserId());
        review.setContent(dto.getContent());
        review.setCreatedAt(dto.getCreatedAt());
        return review;
    }

    @Override
    public ReviewDto submitReview(ReviewDto reviewDto) {
        Review review = toEntity(reviewDto);
        Review saved = reviewRepository.save(review);
        return toDto(saved);
    }

    @Override
    public List<ReviewDto> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId).stream().map(this::toDto).collect(Collectors.toList());
    }
} 