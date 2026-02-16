package com.example.airbnbproject.repository;

import com.example.airbnbproject.repository.OrderItemRepository;
import com.example.airbnbproject.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}