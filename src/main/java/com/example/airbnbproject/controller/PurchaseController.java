package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Purchase;
import com.example.airbnbproject.service.PurchaseService; // Sahi import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService; // Interface use karein

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(
            @RequestParam Long productId,
            @RequestParam Long vendorId,
            @RequestParam int quantity,
            @RequestParam double cost) {
        try {
            Purchase purchase = purchaseService.createPurchase(productId, vendorId, quantity, cost);
            return ResponseEntity.ok(purchase);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}