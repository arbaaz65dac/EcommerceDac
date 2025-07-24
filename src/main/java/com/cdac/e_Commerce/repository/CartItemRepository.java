package com.cdac.e_Commerce.repository;

import com.cdac.e_Commerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
} 