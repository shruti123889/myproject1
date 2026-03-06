package com.example.airbnbproject.controller;

import com.example.airbnbproject.dto.PurchaseRequestDto;
import com.example.airbnbproject.entity.Purchase;
import com.example.airbnbproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
// Origins mein "*" dalne se CORS error khatam ho jayega
@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDto request) {
        try {
            Object purchase = purchaseService.createPurchase(
                    request.getProductId(),
                    1L,
                    request.getQuantity(),
                    request.getUnitCost()
            );
            return ResponseEntity.ok(purchase);
        } catch (Exception e) {
            // Plain string ki jagah JSON object bhejein
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}