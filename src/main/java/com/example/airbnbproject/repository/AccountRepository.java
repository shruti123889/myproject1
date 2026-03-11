package com.example.airbnbproject.repository;

import com.example.airbnbproject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
        // Total Income nikalne ke liye
        @Query("SELECT SUM(a.amount) FROM Account a WHERE a.transactionType = 'INCOME'")
        Double getTotalIncome();

        // Total Expense nikalne ke liye
        @Query("SELECT SUM(a.amount) FROM Account a WHERE a.transactionType = 'EXPENSE'")
        Double getTotalExpense();
}
