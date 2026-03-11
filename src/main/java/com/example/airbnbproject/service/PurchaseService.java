package com.example.airbnbproject.service;

import com.example.airbnbproject.entity.Purchase;

public interface PurchaseService {
    // Sirf yahi method signature rakhein
    Purchase createPurchase(Long productId, Long vendorId, Integer quantity, Double purchasePrice);
}