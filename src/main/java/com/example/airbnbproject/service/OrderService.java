package com.example.airbnbproject.service;
import com.example.airbnbproject.dto.OrderRequestDTO;
import com.example.airbnbproject.dto.OrderResponseDTO;
import com.example.airbnbproject.entity.Order;
import com.example.airbnbproject.dto.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    void deleteOrder(Long id);

int multiply(int price, int quantity);

OrderResponseDTO save (OrderRequestDTO request);

}