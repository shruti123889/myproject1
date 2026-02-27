package com.example.airbnbproject.service.impl;

import com.example.airbnbproject.entity.*;
import com.example.airbnbproject.repository.*;
import com.example.airbnbproject.service.SaleService; // Ye zaroori hai
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Sale createSale(Long productId, Long customerId, int quantity, double price) {
        // 1. Product & Customer check
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product nahi mila!"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer nahi mila!"));

        // 2. Stock check
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Stock kam hai! Sirf " + product.getQuantity() + " bache hain.");
        }

        // 3. Stock update
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        // 4. Sale save
        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setCustomer(customer);
        sale.setQuantity(quantity);
        sale.setSalePrice(price);
        Sale savedSale = saleRepository.save(sale);

        // 5. Transaction record
        Transaction transaction = new Transaction();
        transaction.setAmount(price * quantity);
        transaction.setType(Transaction.TransactionType.CREDIT);
        transaction.setDescription("Sale of: " + product.getName());
        transactionRepository.save(transaction);

        return savedSale;
    }
}