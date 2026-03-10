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
@RequestMapping("/products") // Frontend se match karein
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long totalProducts = productRepository.count();
        long totalPurchases = purchaseRepository.count();
        long totalSales = saleRepository.count();

        Integer totalQuantity = productRepository.findAll().stream()
                .mapToInt(p -> p.getQuantity())
                .sum();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", totalProducts);
        stats.put("totalQuantity", totalQuantity);
        stats.put("totalPurchases", totalPurchases);
        stats.put("totalSales", totalSales);
        stats.put("totalTransactions", transactionRepository.count());

        return stats;
    }


    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}