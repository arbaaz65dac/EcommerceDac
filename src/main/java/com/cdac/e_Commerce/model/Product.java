package com.cdac.e_Commerce.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double defaultPrice;
    private Integer buyerCount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TierPrice> tierPrices;

    public Product() {}
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getDefaultPrice() { return defaultPrice; }
    public void setDefaultPrice(Double defaultPrice) { this.defaultPrice = defaultPrice; }
    public Integer getBuyerCount() { return buyerCount; }
    public void setBuyerCount(Integer buyerCount) { this.buyerCount = buyerCount; }
    public List<TierPrice> getTierPrices() { return tierPrices; }
    public void setTierPrices(List<TierPrice> tierPrices) { this.tierPrices = tierPrices; }
} 