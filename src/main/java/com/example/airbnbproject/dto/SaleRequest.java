package com.example.airbnbproject.dto;

public class SaleRequest {
    public Long productId;
    public Long customerId;
    public int quantity;
    public double price;

    // Getters and Setters (Zaroori hain)
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}