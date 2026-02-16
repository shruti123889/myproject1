import com.example.airbnbproject.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.airbnbproject.controller.OrderController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.airbnbproject.dto.OrderRequestDTO;
import com.example.airbnbproject.dto.OrderResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.airbnbproject.exception.OrderNotFoundException;
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testSaveOrder() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setAmount(500.0);
        OrderResponseDTO response = new OrderResponseDTO();
        response.setAmount(500.0);
        when(service.save(any(OrderRequestDTO.class)))
                .thenReturn(response);
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(500.0));
    }
    @Test
    void getOrderById_notFound() throws Exception {
        when(service.getOrderById(99L))
                .thenThrow(new OrderNotFoundException("Order not found with id 99"));
        mockMvc.perform(get("/orders/99"))
                .andExpect(status().isNotFound());
    }
}