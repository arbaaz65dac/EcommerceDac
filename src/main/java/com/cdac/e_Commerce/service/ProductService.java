package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto getProductByName(String name);
    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    Double getProductPrice(Long id);
    List<Object> getProductTiers(Long id);
} 