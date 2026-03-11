package com.example.airbnbproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "payrolls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private String month; // e.g., "March 2026"
    private Double basicSalary;
    private Double bonus;
    private Double deductions; // Tax, Leaves, etc.
    private Double netSalary; // (Basic + Bonus - Deductions)
    private LocalDate paymentDate;

    private String status; // "PAID", "PENDING"
}
