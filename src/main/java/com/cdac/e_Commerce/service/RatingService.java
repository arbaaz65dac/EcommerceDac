package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.RatingDto;
import java.util.List;

public interface RatingService {
    RatingDto submitRating(RatingDto ratingDto);
    List<RatingDto> getRatingsForProduct(Long productId);
} 