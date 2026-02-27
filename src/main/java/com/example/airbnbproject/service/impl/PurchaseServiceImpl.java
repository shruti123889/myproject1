package com.example.airbnbproject.service.impl;

import com.example.airbnbproject.entity.*;
import com.example.airbnbproject.repository.*;
import com.example.airbnbproject.service.PurchaseService; // Ye import bahut zaroori hai
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorRepository vendorRepository;

    @Override // Ye annotation lagayein
    @Transactional
    public Purchase createPurchase(Long productId, Long vendorId, int quantity, double cost) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product nahi mila!"));
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor nahi mila!"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setVendor(vendor);
        purchase.setQuantity(quantity);
        purchase.setCostPrice(cost);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Transaction logic yahan method ke andar hona chahiye
        Transaction transaction = new Transaction();
        transaction.setAmount(cost * quantity);
        transaction.setType(Transaction.TransactionType.DEBIT);
        transaction.setDescription("Purchase of: " + product.getName());
        transactionRepository.save(transaction);

        return savedPurchase;
    }
}