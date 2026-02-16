package com.example.airbnbproject.dto;

public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private int quantity;

    public ProductDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // 🔥 YE MISSING THA
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}