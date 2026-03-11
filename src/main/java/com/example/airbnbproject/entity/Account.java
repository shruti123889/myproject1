package com.example.airbnbproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType; // "INCOME" or "EXPENSE"
    private Double amount;
    private String description; // e.g., "Sale of Product X" or "Salary for March"
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // CASH, ONLINE, CHEQUE
}