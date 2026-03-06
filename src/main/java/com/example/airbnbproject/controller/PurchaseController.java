package com.example.airbnbproject.controller;

import com.example.airbnbproject.dto.PurchaseRequestDto;
import com.example.airbnbproject.entity.Purchase;
import com.example.airbnbproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
// Origins mein "*" dalne se CORS error khatam ho jayega
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Iska full path ab "BASE_URL/purchase/create" hoga
    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDto request) {
        try {
            Purchase purchase = purchaseService.createPurchase(
                    request.getProductId(),
                    1L, // Default Vendor ID
                    request.getQuantity(),
                    request.getUnitCost()
            );
            return ResponseEntity.ok(purchase);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}