package com.example.airbnbproject.controller;
import java.util.List;
import com.example.airbnbproject.entity.Sale;
import com.example.airbnbproject.service.SaleService;
import com.example.airbnbproject.dto.SaleRequest; // Step 1 wali class import karein
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // 1. Create Sale (Jo aapke paas pehle se tha)
    @PostMapping("/create")
    public ResponseEntity<?> createSale(@RequestBody SaleRequest request) {
        try {
            Sale sale = saleService.createSale(
                    request.getProductId(),
                    request.getCustomerId(),
                    request.getQuantity(),
                    request.getPrice()
            );
            return ResponseEntity.ok(sale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // 2. Get All Sales (Sari sales dekhne ke liye)
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }
    // 3. Get Sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody SaleRequest request) {
        try {
            Sale updatedSale = saleService.updateSale(id, request);
            return ResponseEntity.ok(updatedSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // 4. Delete Sale
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        try {
            saleService.deleteSale(id);
            return ResponseEntity.ok("Sale deleted successfully with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
