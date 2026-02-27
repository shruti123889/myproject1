package com.example.airbnbproject.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description; // Udaharan: "Sale of Product X" ya "Purchase from Vendor Y"

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // CREDIT ya DEBIT

    private LocalDateTime transactionDate;

    public enum TransactionType {
        CREDIT, DEBIT
    }

    public Transaction() {
        this.transactionDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
}
