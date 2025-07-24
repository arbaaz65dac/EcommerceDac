package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.ProductDto;
import com.cdac.e_Commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class UserProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getProductPrice(@PathVariable Long id) {
        Double price = productService.getProductPrice(id);
        if (price == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(price);
    }

    @GetMapping("/{id}/tiers")
    public ResponseEntity<List<Object>> getProductTiers(@PathVariable Long id) {
        List<Object> tiers = productService.getProductTiers(id);
        if (tiers == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tiers);
    }
} 