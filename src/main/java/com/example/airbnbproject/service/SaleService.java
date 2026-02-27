package com.example.airbnbproject.service;


import com.example.airbnbproject.entity.Sale;

public interface SaleService {
    Sale createSale(Long productId, Long customerId, int quantity, double price);
}