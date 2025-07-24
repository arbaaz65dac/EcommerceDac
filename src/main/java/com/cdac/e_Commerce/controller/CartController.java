package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.CartDto;
import com.cdac.e_Commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(@RequestParam Long userId) {
        CartDto cart = cartService.getCartByUserId(userId);
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartDto> createOrResetCart(@RequestParam Long userId) {
        CartDto cart = cartService.createOrResetCart(userId);
        return ResponseEntity.ok(cart);
    }
} 