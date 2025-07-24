package com.cdac.e_Commerce.model;

import jakarta.persistence.*;

@Entity
public class TierPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minBuyers;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public TierPrice() {}
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getMinBuyers() { return minBuyers; }
    public void setMinBuyers(Integer minBuyers) { this.minBuyers = minBuyers; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
} 