package com.cdac.e_Commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping
    public ResponseEntity<Object> getHomeContent() {
        // Placeholder content
        Map<String, Object> home = new HashMap<>();
        home.put("banners", Arrays.asList("Welcome to Multi-Price E-Commerce!", "Big Sale!", "Tiered Pricing for All!"));
        home.put("featuredProducts", Arrays.asList(101, 102, 103)); // Example product IDs
        return ResponseEntity.ok(home);
    }
} 