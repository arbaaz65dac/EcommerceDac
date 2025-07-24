package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.RatingService;
import com.cdac.e_Commerce.dto.RatingDto;
import com.cdac.e_Commerce.model.Rating;
import com.cdac.e_Commerce.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    private RatingDto toDto(Rating rating) {
        RatingDto dto = new RatingDto();
        dto.setId(rating.getId());
        dto.setProductId(rating.getProductId());
        dto.setUserId(rating.getUserId());
        dto.setScore(rating.getScore());
        dto.setComment(rating.getComment());
        return dto;
    }

    private Rating toEntity(RatingDto dto) {
        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setProductId(dto.getProductId());
        rating.setUserId(dto.getUserId());
        rating.setScore(dto.getScore());
        rating.setComment(dto.getComment());
        return rating;
    }

    @Override
    public RatingDto submitRating(RatingDto ratingDto) {
        Rating rating = toEntity(ratingDto);
        Rating saved = ratingRepository.save(rating);
        return toDto(saved);
    }

    @Override
    public List<RatingDto> getRatingsForProduct(Long productId) {
        return ratingRepository.findByProductId(productId).stream().map(this::toDto).collect(Collectors.toList());
    }
} 