package com.example.airbnbproject.service;

import com.example.airbnbproject.entity.Purchase;

public interface PurchaseService {
    Purchase createPurchase(Long productId, Long vendorId, int quantity, double cost);
}