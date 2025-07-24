package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.ProductService;
import com.cdac.e_Commerce.dto.ProductDto;
import com.cdac.e_Commerce.model.Product;
import com.cdac.e_Commerce.model.TierPrice;
import com.cdac.e_Commerce.repository.ProductRepository;
import com.cdac.e_Commerce.repository.TierPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TierPriceRepository tierPriceRepository;

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setDefaultPrice(product.getDefaultPrice());
        dto.setBuyerCount(product.getBuyerCount());
        dto.setTierPrices(product.getTierPrices() != null ? product.getTierPrices().stream().map(tp -> {
            // Simple map for now
            return tp;
        }).collect(Collectors.toList()) : null);
        return dto;
    }

    private Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setDefaultPrice(dto.getDefaultPrice());
        product.setBuyerCount(dto.getBuyerCount());
        // TierPrices mapping skipped for brevity
        return product;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::toDto).orElse(null);
    }

    @Override
    public ProductDto getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.map(this::toDto).orElse(null);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setDefaultPrice(productDto.getDefaultPrice());
            product.setBuyerCount(productDto.getBuyerCount());
            // TierPrices mapping skipped for brevity
            Product saved = productRepository.save(product);
            return toDto(saved);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Double getProductPrice(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            int buyers = product.getBuyerCount() != null ? product.getBuyerCount() : 0;
            // Tiered pricing logic
            if (buyers >= 100000) return 25.0;
            if (buyers >= 50000) return 50.0;
            if (buyers >= 25000) return 100.0;
            return product.getDefaultPrice() != null ? product.getDefaultPrice() : 200.0;
        }
        return null;
    }

    @Override
    public List<Object> getProductTiers(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            return product.getTierPrices().stream().map(tp -> tp).collect(Collectors.toList());
        }
        return null;
    }
} 