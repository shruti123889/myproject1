package com.example.airbnbproject.controller;
import com.example.airbnbproject.entity.Transaction;
import com.example.airbnbproject.repository.PurchaseRepository;


import com.example.airbnbproject.repository.ProductRepository;
import com.example.airbnbproject.repository.PurchaseRepository;
import com.example.airbnbproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TransactionRepository transactionRepository; // Ye line add karein

    @GetMapping
    public Map<String, Object> getStats() {
        // Database se asli numbers nikalna
        long totalProducts = productRepository.count();
        Integer totalQuantity = productRepository.findAll().stream()
                .mapToInt(p -> p.getQuantity()) // Yahan se '!= null' wala part hata dein
                .sum();

        long totalPurchases = purchaseRepository.count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", totalProducts);
        stats.put("totalQuantity", totalQuantity);
        stats.put("totalPurchases", totalPurchases);

        return stats;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

