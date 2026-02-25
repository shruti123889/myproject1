package com.example.airbnbproject.repository;

import com.example.airbnbproject.entity.Product;
import com.example.airbnbproject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Find inventory by product
    Optional<Inventory> findByProduct(Product product);

}
