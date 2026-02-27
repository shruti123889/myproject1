package com.example.airbnbproject.service.impl;

import com.example.airbnbproject.repository.TransactionRepository;
import com.example.airbnbproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public double getTotalIncome() {
        Double income = transactionRepository.getTotalIncome();
        return (income != null) ? income : 0.0;
    }

    @Override
    public double getTotalExpense() {
        Double expense = transactionRepository.getTotalExpense();
        return (expense != null) ? expense : 0.0;
    }

    // YE METHOD MISSING THA, ISSE ADD KAREIN:
    @Override
    public double calculateProfit() {
        return getTotalIncome() - getTotalExpense();
    }
}