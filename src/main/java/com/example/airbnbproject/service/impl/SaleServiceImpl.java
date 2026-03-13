package com.example.airbnbproject.service.impl;
import com.example.airbnbproject.dto.SaleRequest;
import com.example.airbnbproject.entity.*;
import com.example.airbnbproject.repository.*;
import com.example.airbnbproject.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.airbnbproject.entity.Account;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AccountRepository accountRepository; // Pehle yahan OrderServiceImpl likha tha, ise ab AccountRepository kar dein.

    @Override
    @Transactional
    public Sale createSale(Long productId, Long customerId, int quantity, double price) {
        // 1. Existing Logic: Product aur Customer check karna
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product nahi mila!"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer nahi mila!"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Stock kam hai! Sirf " + product.getQuantity() + " bache hain.");
        }
        // 2. Stock update aur Sale save karna
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setCustomer(customer);
        sale.setQuantity(quantity);
        sale.setSalePrice(price);
        Sale savedSale = saleRepository.save(sale);
        // 3. Transaction Record save karna
        Transaction saleTransaction = new Transaction();
        saleTransaction.setAmount(price * quantity);
        saleTransaction.setDescription("Sale of: " + product.getName() + " (Qty: " + quantity + ")");
        saleTransaction.setTransactionDate(LocalDate.now());
        transactionRepository.save(saleTransaction);
        // 4. Account Table mein Income Entry (Sahi logic)
        Account accountEntry = Account.builder()
                .transactionType("INCOME")
                .amount(price * quantity)
                .description("Revenue from Sale ID: " + savedSale.getId())
                .transactionDate(LocalDate.now())
                .build();
        // Yahan ab error nahi aayega kyunki humne upar accountRepository ko sahi kar diya hai
        accountRepository.save(accountEntry);
        return savedSale;
    }

        // YE METHODS createSale KE BAHAR HONI CHAHIYE
    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    @Override
    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }
    @Override
    public void deleteSale(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Sale record nahi mila ID: " + id);
        }
    }@Override
    @Transactional
    public Sale updateSale(Long id, SaleRequest request) {
        // 1. Purani sale dhundo
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale record nahi mila!"));

        // 2. Naya Product aur Customer check karo
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product nahi mila!"));
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer nahi mila!"));
        // 3. Values update karo
        existingSale.setProduct(product);
        existingSale.setCustomer(customer);
        existingSale.setQuantity(request.getQuantity());
        existingSale.setSalePrice(request.getPrice());
        // 4. Save karke return karo
        return saleRepository.save(existingSale);
    }
}