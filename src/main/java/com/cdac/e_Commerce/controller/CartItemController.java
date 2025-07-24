package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.CartDto;
import com.cdac.e_Commerce.dto.CartItemDto;
import com.cdac.e_Commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/items")
public class CartItemController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addCartItem(@RequestParam Long userId, @RequestBody CartItemDto cartItemDto) {
        CartDto cart = cartService.addItemToCart(userId, cartItemDto.getProductId(), cartItemDto.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCartItem(@RequestParam Long userId, @PathVariable Long id, @RequestBody CartItemDto cartItemDto) {
        CartDto cart = cartService.updateCartItem(userId, id, cartItemDto.getQuantity());
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@RequestParam Long userId, @PathVariable Long id) {
        cartService.removeCartItem(userId, id);
        return ResponseEntity.noContent().build();
    }
} 