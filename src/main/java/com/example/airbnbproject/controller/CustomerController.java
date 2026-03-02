package com.example.airbnbproject.controller;
import java.util.List;
import com.example.airbnbproject.entity.Customer;
import com.example.airbnbproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) { // Map ki jagah Customer entity use karein
        try {
            // Ye line database mein table banayegi aur data save karegi
            Customer savedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok("Customer saved in DB with ID: " + savedCustomer.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Sabhi customers fetch karne ke liye
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
    // Testing ke liye ek GET method bhi rakh lete hain
    @GetMapping("/test")
    public String test() {
        return "Customer Controller is working!";
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setContact(customerDetails.getContact());
            customer.setEmail(customerDetails.getEmail());
            customerRepository.save(customer);
            return ResponseEntity.ok("Customer updated successfully with ID: " + id);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                return ResponseEntity.ok("Customer deleted successfully with ID: " + id);
            } else {
                return ResponseEntity.status(404).body("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}


