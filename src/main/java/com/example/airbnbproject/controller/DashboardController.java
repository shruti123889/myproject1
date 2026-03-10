package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Transaction;
import com.example.airbnbproject.repository.ProductRepository;
import com.example.airbnbproject.repository.PurchaseRepository;
import com.example.airbnbproject.repository.SaleRepository;
import com.example.airbnbproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.sum;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SaleRepository saleRepository; // Ye line add karein

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        // 1. Database se counts nikaalein
        long totalProducts = productRepository.count();
        long totalPurchases = purchaseRepository.count();
        long totalSales = saleRepository.count(); // Naya count

        // 2. Total Quantity ka logic (Jo aapne pehle likha tha)
        Integer totalQuantity = productRepository.findAll().stream()
                .mapToInt(p -> p.getQuantity())
                .sum();

        // 3. Map mein data bharein
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", totalProducts);
        stats.put("totalQuantity", totalQuantity);
        stats.put("totalPurchases", totalPurchases);
        stats.put("totalSales", totalSales); // Frontend ko bhejane ke liye

        return stats;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}