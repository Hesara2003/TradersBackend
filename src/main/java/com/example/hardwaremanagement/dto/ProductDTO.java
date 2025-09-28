package com.example.hardwaremanagement.dto;

import java.util.List;

public class ProductDTO {
    private String name;
    private double price;
    private double discountedPrice;
    private Double discountPercent; // null if no promotion
    private String promotionName; // null if no promotion
    private List<String> images;
    private String category; // category name

    // Constructor (full)
    public ProductDTO(String name, double price, double discountedPrice, Double discountPercent, String promotionName, List<String> images, String category) {
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountPercent = discountPercent;
        this.promotionName = promotionName;
        this.images = images;
        this.category = category;
    }

    // Convenience constructor without promotion
    public ProductDTO(String name, double price, List<String> images, String category) {
        this(name, price, price, null, null, images, category);
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }

    public Double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Double discountPercent) { this.discountPercent = discountPercent; }

    public String getPromotionName() { return promotionName; }
    public void setPromotionName(String promotionName) { this.promotionName = promotionName; }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}