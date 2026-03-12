package com.example.airbnbproject.service.impl;

import com.example.airbnbproject.entity.*;
import com.example.airbnbproject.repository.*;
import com.example.airbnbproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Purchase createPurchase(Long productId, Long vendorId, Integer quantity, Double purchasePrice) {
        // 1. Check Product aur Vendor
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product nahi mila!"));
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        if (vendor == null) {
            vendor = new Vendor();
            vendor.setId(1L);
            vendor.setName("Dummy Vendor");
        }
        // 2. Inventory Management (Stock badhana)
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        // 3. Purchase Record Save
        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setVendor(vendor);
        purchase.setQuantity(quantity);
        purchase.setPurchasePrice(purchasePrice);

        // YAHAN FIX HAI: Ensure karein ki ye aapki Entity ke type se match kare
        purchase.setPurchaseDate(java.time.LocalDateTime.now());


        Purchase savedPurchase = purchaseRepository.save(purchase);

        // 4. Ledger Transaction
        Transaction purchaseTrans = new Transaction();
        purchaseTrans.setAmount(purchasePrice * quantity);
        purchaseTrans.setDescription("Stock Purchase: " + product.getName());
        purchaseTrans.setTransactionDate(LocalDate.now());
        transactionRepository.save(purchaseTrans);

        // 5. Account Expense Entry
        Account accountEntry = Account.builder()
                .transactionType("EXPENSE")
                .amount(purchasePrice * quantity)
                .description("Purchase from Vendor ID: " + vendorId)
                .transactionDate(LocalDate.now())
                .build();

        accountRepository.save(accountEntry);

        return savedPurchase;
    }
}