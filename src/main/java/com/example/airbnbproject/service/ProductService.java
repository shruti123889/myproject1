package com.example.airbnbproject.service;

import com.example.airbnbproject.dto.ProductRequestDto;
import com.example.airbnbproject.entity.Product;
import org.springframework.security.core.parameters.P;
import com.example.airbnbproject.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductRequestDto dto);
    Product save(ProductRequestDto dto);
    Page<ProductDto> getProducts(int page, int size, String name);

}
