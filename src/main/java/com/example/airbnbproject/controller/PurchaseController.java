package com.example.airbnbproject.controller;

import com.example.airbnbproject.dto.PurchaseRequestDto;
import com.example.airbnbproject.entity.Purchase;
import com.example.airbnbproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase") // Frontend ki URL se match karne ke liye change kiya
@CrossOrigin(origins = "http://127.0.0.1:5500") // CORS Error fix karne ke liye
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDto request) { // RequestBody use karein
        try {
            // request object se data nikalein
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