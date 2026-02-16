package com.example.airbnbproject.dto;
import com.example.airbnbproject.dto.OrderItemDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
public class OrderRequestDTO {

    @NotNull
    @Min(1)
    private Double amount;
    @NotNull(message = "Order number cannot be null")
    private String orderNumber;

    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemDTO> orderItems;

    //getters   & setters

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
