package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Sale;
import com.example.airbnbproject.service.SaleService; // Sahi import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService; // Interface use karein

    @PostMapping("/create")
    public ResponseEntity<?> createSale(
            @RequestParam Long productId,
            @RequestParam Long customerId,
            @RequestParam int quantity,
            @RequestParam double price) {
        try {
            Sale sale = saleService.createSale(productId, customerId, quantity, price);
            return ResponseEntity.ok(sale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}