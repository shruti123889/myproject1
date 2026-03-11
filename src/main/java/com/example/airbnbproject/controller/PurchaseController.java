package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Purchase;
import com.example.airbnbproject.dto.PurchaseRequestDto; // Ensure aapne ye DTO banaya ho
import com.example.airbnbproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases") // URL: /purchases (api prefix hatane ke baad)
@CrossOrigin("*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequestDto request) {
        Purchase purchase = purchaseService.createPurchase(
                request.getProductId(),
                request.getVendorId(),
                request.getQuantity(),
                request.getPurchasePrice()
        );
        return ResponseEntity.ok(purchase);
    }
}