package com.example.airbnbproject.repository;
import com.example.airbnbproject.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    // Kisi specific product ki saari purchases dekhne ke liye
    List<Purchase> findByProductId(Long productId);

    // Future use ke liye: Vendor ke naam se search karne ke liye
    // List<Purchase> findByVendorNameContaining(String vendorName);
}