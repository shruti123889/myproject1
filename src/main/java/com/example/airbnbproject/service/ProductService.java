package com.example.airbnbproject.service;

import com.example.airbnbproject.dto.ProductRequestDto;
import com.example.airbnbproject.entity.Product;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductRequestDto dto);
    Product save(ProductRequestDto dto);


}
