package com.example.airbnbproject.service;

import com.example.airbnbproject.dto.SaleRequest;
import com.example.airbnbproject.entity.Sale;
import java.util.List;
import java.util.Optional;

public interface SaleService {
    // Existing method
    Sale createSale(Long productId, Long customerId, int quantity, double price);

    // Naye methods add karein
    List<Sale> getAllSales();

    Optional<Sale> getSaleById(Long id);

    void deleteSale(Long id);
    Sale updateSale(Long id, SaleRequest request);

}
