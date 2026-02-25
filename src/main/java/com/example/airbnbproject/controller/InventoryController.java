package com.example.airbnbproject.controller;


import com.example.airbnbproject.entity.Inventory;
import com.example.airbnbproject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add Stock
    @PostMapping("/add")
    public Inventory addStock(@RequestParam Long productId,
                              @RequestParam Integer quantity) {
        return inventoryService.addStock(productId, quantity);
    }

    // Reduce Stock
    @PostMapping("/reduce")
    public Inventory reduceStock(@RequestParam Long productId,
                                 @RequestParam Integer quantity) {
        return inventoryService.reduceStock(productId, quantity);
    }

    // Get Stock
    @GetMapping("/{productId}")
    public Integer getStock(@PathVariable Long productId) {
        return inventoryService.getStock(productId);
    }
}