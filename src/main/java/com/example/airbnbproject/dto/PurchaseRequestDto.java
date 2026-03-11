package com.example.airbnbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {
    private Long productId;
    private Long vendorId;
    private int quantity;
    private double purchasePrice;

}