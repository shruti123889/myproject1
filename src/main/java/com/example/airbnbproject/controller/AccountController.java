package com.example.airbnbproject.controller;

// Dhyaan dein: Interface import hona chahiye, Impl nahi
import com.example.airbnbproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getAccountSummary() {
        Map<String, Object> summary = new HashMap<>();

        double totalIncome = accountService.getTotalIncome();
        double totalExpense = accountService.getTotalExpense();
        double profit = accountService.calculateProfit();

        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("netProfit", profit);
        summary.put("status", profit >= 0 ? "Profit" : "Loss");

        return ResponseEntity.ok(summary);
    }
}