package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.CartDto;

public interface CartService {
    CartDto getCartByUserId(Long userId);
    CartDto createOrResetCart(Long userId);
    CartDto addItemToCart(Long userId, Long productId, int quantity);
    CartDto updateCartItem(Long userId, Long cartItemId, int quantity);
    void removeCartItem(Long userId, Long cartItemId);
} 