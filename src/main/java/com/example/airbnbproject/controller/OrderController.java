package com.example.airbnbproject.controller;
import com.example.airbnbproject.entity.Order;
import com.example.airbnbproject.service.OrderService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.airbnbproject.dto.OrderRequestDTO;
import com.example.airbnbproject.dto.OrderResponseDTO;
import java.util.Optional;
import com.example.airbnbproject.exception.OrderNotFoundException;
import com.example.airbnbproject.dto.OrderRequestDTO;
@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(
            @Valid @RequestBody OrderRequestDTO dto) {

        OrderResponseDTO savedOrder = orderService.save(dto);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }



}
