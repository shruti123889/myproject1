package com.example.airbnbproject.repository;
import com.example.airbnbproject.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Agar aapko kisi specific product ki saari sales dekhni hon
    List<Sale> findByProductId(Long productId);

    // Future use ke liye: Date wise sales nikalne ke liye
    // List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);
}
