package com.example.airbnbproject.service;


import com.example.airbnbproject.entity.Employee;
import com.example.airbnbproject.entity.Payroll;
import com.example.airbnbproject.repository.EmployeeRepository;
import com.example.airbnbproject.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Payroll generatePayroll(Long employeeId, Double bonus, Double deductions) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Double netSalary = emp.getBaseSalary() + bonus - deductions;

        Payroll payroll = Payroll.builder()
                .employee(emp)
                .month(LocalDate.now().getMonth().toString())
                .basicSalary(emp.getBaseSalary())
                .bonus(bonus)
                .deductions(deductions)
                .netSalary(netSalary)
                .paymentDate(LocalDate.now())
                .status("PAID")
                .build();

        return payrollRepository.save(payroll);
    }
}
