package com.example.airbnbproject.dto;

public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    // Report ke liye nayi fields
    private double totalStockValue;
    private String status;

    // 1. Default Constructor (Spring/Jackson ke liye zaroori hai)
    public ProductDto() {}

    // 2. All-Args Constructor (Manually data set karne ke liye)
    public ProductDto(Long id, String name, double price, int quantity, double totalStockValue, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalStockValue = totalStockValue;
        this.status = status;
    }

    // 3. Saare Getters aur Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalStockValue() { return totalStockValue; }
    public void setTotalStockValue(double totalStockValue) { this.totalStockValue = totalStockValue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}