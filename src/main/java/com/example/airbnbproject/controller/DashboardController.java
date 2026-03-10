package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Transaction;
import com.example.airbnbproject.repository.ProductRepository;
import com.example.airbnbproject.repository.PurchaseRepository;
import com.example.airbnbproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.sum;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long totalProducts = productRepository.count();

        // Yahan Integer (Wrapper class) use karein ya direct check hatayein
        // Kyunki primitive int null nahi ho sakta
        int totalQuantity = productRepository.findAll().stream()
                .mapToInt(p -> p.getQuantity()) // Agar entity mein int hai toh null check ki zaroorat nahi
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