package com.cdac.e_Commerce.dto;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double defaultPrice;
    private List<Object> tierPrices;
    private Integer buyerCount;

    public ProductDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getDefaultPrice() { return defaultPrice; }
    public void setDefaultPrice(Double defaultPrice) { this.defaultPrice = defaultPrice; }
    public List<Object> getTierPrices() { return tierPrices; }
    public void setTierPrices(List<Object> tierPrices) { this.tierPrices = tierPrices; }
    public Integer getBuyerCount() { return buyerCount; }
    public void setBuyerCount(Integer buyerCount) { this.buyerCount = buyerCount; }
} 