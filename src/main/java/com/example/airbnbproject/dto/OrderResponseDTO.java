package com.example.airbnbproject.dto;

import com.example.airbnbproject.entity.OrderItem;

import java.util.List;
public class OrderResponseDTO {
    private Long id;
    private String orderNumber;
    private String status;
    private List<OrderItemDTO> orderItems;


    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
