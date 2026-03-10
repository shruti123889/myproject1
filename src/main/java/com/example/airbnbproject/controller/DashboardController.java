package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Product;
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
    private SaleRepository saleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // Direct counts from repositories
        stats.put("totalProducts", productRepository.count());
        stats.put("totalPurchases", purchaseRepository.count());
        stats.put("totalSales", saleRepository.count());
        stats.put("totalTransactions", transactionRepository.count());

        // Efficient way to get total quantity

        Integer totalQuantity = productRepository.findAll()
                .stream()
                .mapToInt(Product::getQuantity) // Direct call, no null check needed for primitive int
                .sum();
        stats.put("totalQuantity", totalQuantity);

        return stats;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}