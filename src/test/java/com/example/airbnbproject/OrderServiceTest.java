package com.example.airbnbproject;
import com.example.airbnbproject.exception.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.airbnbproject.service.OrderService;
import com.example.airbnbproject.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.airbnbproject.repository.OrderRepository;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import com.example.airbnbproject.entity.Order;
import com.example.airbnbproject.dto.OrderRequestDTO;
import static org.mockito.Mockito.*;
import com.example.airbnbproject.dto.OrderResponseDTO;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderServiceImpl service;

    @Test
    void saveOrderTest() {

        OrderRequestDTO request = new OrderRequestDTO();
        request.setAmount(500.0);

        Order order = new Order();
        order.setAmount(500.0);

        when(repository.save(any(Order.class)))
                .thenReturn(order);

        OrderResponseDTO response = service.save(request);

        assertEquals(500.0, response.getAmount());

        verify(repository, times(1)).save(any(Order.class));
    }
    @Test
    void saveOrder_shouldThrowException_whenAmountIsZero() {

        OrderRequestDTO request = new OrderRequestDTO();
        request.setAmount(0.0);

        assertThrows(IllegalArgumentException.class, () -> {
            service.save(request);
        });
    }
    @Test
    void getOrderById_shouldThrowException_whenOrderNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            service.getOrderById(1L);
        });

        verify(repository, times(1)).findById(1L);
    }


}