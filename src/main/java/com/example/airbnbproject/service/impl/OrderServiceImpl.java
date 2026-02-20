package com.example.airbnbproject.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import com.example.airbnbproject.service.OrderService;
import com.example.airbnbproject.repository.OrderRepository;
import com.example.airbnbproject.dto.OrderRequestDTO;
import com.example.airbnbproject.dto.OrderResponseDTO;
import com.example.airbnbproject.dto.OrderItemDTO;
import com.example.airbnbproject.entity.Order;
import com.example.airbnbproject.entity.OrderItem;
import com.example.airbnbproject.repository.OrderItemRepository;
import com.example.airbnbproject.exception.OrderNotFoundException;
@Service
public class OrderServiceImpl implements OrderService {
        @Autowired
        private OrderRepository orderRepository;
        @Autowired
        private OrderItemRepository orderItemRepository;
    @Override
    public int multiply(int price, int quantity) {
        return price * quantity;
    }
        @Override
        public OrderResponseDTO createOrder(OrderRequestDTO request) {
            Order order = new Order();
            order.setOrderNumber(request.getOrderNumber());
            List<OrderItem> items = new ArrayList<>();
            for (OrderItemDTO dto : request.getOrderItems()) {
                OrderItem item = new OrderItem();
                item.setProductId(dto.getProductId());
                item.setQuantity(dto.getQuantity());
                item.setOrder(order);

                items.add(item);
            }
            order.setOrderItems(items);
            Order savedOrder = orderRepository.save(order);
            OrderResponseDTO response = new OrderResponseDTO();
            response.setId(savedOrder.getId());
            response.setOrderNumber(savedOrder.getOrderNumber());
            response.setStatus("CREATED");
            return response;
        }
        @Override
        public List<Order> getAllOrders() {

            return orderRepository.findAll();
        }
    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found with id " + id));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponseDTO save(OrderRequestDTO request) {

        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Order order = new Order();
        order.setOrderNumber(request.getOrderNumber());
        order.setAmount(request.getAmount());
        order.setStatus("CREATED");

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDTO dto : request.getOrderItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(dto.getProductId());
            item.setQuantity(dto.getQuantity());
            item.setOrder(order);
            items.add(item);
        }

        order.setOrderItems(items);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(savedOrder.getId());
        response.setOrderNumber(savedOrder.getOrderNumber());
        response.setStatus(savedOrder.getStatus());
        response.setAmount(savedOrder.getAmount());

        return response;
    }

    }