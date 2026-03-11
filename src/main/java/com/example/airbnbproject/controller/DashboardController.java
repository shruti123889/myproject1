package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Product;
import com.example.airbnbproject.entity.Transaction;
import com.example.airbnbproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.sum;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository; // Financial data ke liye

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // 1. Basic Counts
        stats.put("totalProducts", productRepository.count());
        stats.put("totalPurchases", purchaseRepository.count());
        stats.put("totalSales", saleRepository.count());
        stats.put("totalTransactions", transactionRepository.count());

        // 2. Total Quantity Calculation (Efficient way)
        Integer totalQuantity = productRepository.findAll().stream()
                .mapToInt(product -> product.getQuantity())
                .sum();
        stats.put("totalQuantity", totalQuantity);

        // 3. Financial Stats (Account Repository se)
        Double income = accountRepository.getTotalIncome();
        Double expense = accountRepository.getTotalExpense();

        stats.put("totalIncome", income != null ? income : 0.0);
        stats.put("totalExpense", expense != null ? expense : 0.0);
        stats.put("netProfit", (income != null ? income : 0.0) - (expense != null ? expense : 0.0));

        // NEW: Low Stock Alert Logic (Threshold = 10 units)
        List<Product> lowStockItems = productRepository.findLowStockProducts(10);
        stats.put("lowStockProducts", lowStockItems);
        stats.put("lowStockCount", lowStockItems.size());

        return stats;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}