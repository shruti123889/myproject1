package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.Payroll;
import com.example.airbnbproject.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/generate/{empId}")
    public ResponseEntity<Payroll> processSalary(
            @PathVariable Long empId,
            @RequestParam Double bonus,
            @RequestParam Double deductions) {
        return ResponseEntity.ok(payrollService.generatePayroll(empId, bonus, deductions));
    }
}
