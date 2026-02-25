package com.example.airbnbproject.service;

import com.example.airbnbproject.entity.Product;
import com.example.airbnbproject.repository.InventoryRepository;
import com.example.airbnbproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.airbnbproject.entity.Inventory;
import java.time.LocalDateTime;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add Stock
    public com.example.airbnbproject.entity.Inventory addStock(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        com.example.airbnbproject.entity.Inventory inventory = inventoryRepository.findByProduct(product)
                .orElse(new com.example.airbnbproject.entity.Inventory(product, 0));

        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }
    // Reduce Stock
    public Inventory reduceStock(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        com.example.airbnbproject.entity.Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }

    // Get Stock
    public Integer getStock(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        com.example.airbnbproject.entity.Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        return inventory.getQuantity();
    }
}
