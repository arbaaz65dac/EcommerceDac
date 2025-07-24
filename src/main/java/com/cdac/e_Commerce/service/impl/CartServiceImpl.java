package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.CartService;
import com.cdac.e_Commerce.dto.CartDto;
import com.cdac.e_Commerce.dto.CartItemDto;
import com.cdac.e_Commerce.model.Cart;
import com.cdac.e_Commerce.model.CartItem;
import com.cdac.e_Commerce.repository.CartRepository;
import com.cdac.e_Commerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    private CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setTotalAmount(cart.getTotalAmount());
        dto.setItems(cart.getItems() != null ? cart.getItems().stream().map(this::toDto).collect(Collectors.toList()) : null);
        return dto;
    }

    private CartItemDto toDto(CartItem item) {
        CartItemDto dto = new CartItemDto();
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    private Cart toEntity(CartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        cart.setTotalAmount(dto.getTotalAmount());
        if (dto.getItems() != null) {
            List<CartItem> items = dto.getItems().stream().map(this::toEntity).collect(Collectors.toList());
            items.forEach(i -> i.setCart(cart));
            cart.setItems(items);
        }
        return cart;
    }

    private CartItem toEntity(CartItemDto dto) {
        CartItem item = new CartItem();
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return item;
    }

    @Override
    public CartDto getCartByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.map(this::toDto).orElse(null);
    }

    @Override
    public CartDto createOrResetCart(Long userId) {
        Optional<Cart> existing = cartRepository.findByUserId(userId);
        Cart cart = existing.orElseGet(() -> {
            Cart c = new Cart();
            c.setUserId(userId);
            c.setTotalAmount(0.0);
            return c;
        });
        cart.getItems().clear();
        cart.setTotalAmount(0.0);
        Cart saved = cartRepository.save(cart);
        return toDto(saved);
    }

    @Override
    public CartDto addItemToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart c = new Cart();
            c.setUserId(userId);
            c.setTotalAmount(0.0);
            return cartRepository.save(c);
        });
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPrice(0.0); // TODO: Set price from product
        item.setCart(cart);
        cart.getItems().add(item);
        cart.setTotalAmount(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
        cartRepository.save(cart);
        return toDto(cart);
    }

    @Override
    public CartDto updateCartItem(Long userId, Long cartItemId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            for (CartItem item : cart.getItems()) {
                if (item.getId().equals(cartItemId)) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            cart.setTotalAmount(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
            cartRepository.save(cart);
            return toDto(cart);
        }
        return null;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
            cart.setTotalAmount(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
            cartRepository.save(cart);
        }
    }
} 