package com.example.airbnbproject.service.impl;
import com.example.airbnbproject.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.airbnbproject.entity.Product;
import com.example.airbnbproject.repository.ProductRepository;
import com.example.airbnbproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.airbnbproject.exception.ProductNotFoundException;
import java.util.List;
import com.example.airbnbproject.dto.ProductRequestDto;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    //  Get product by id
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );
    }//  Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Page<ProductDto> getProducts(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        if (name != null && !name.isEmpty()) {
            productPage = productRepository
                    .findByNameContainingIgnoreCase(name, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }return productPage.map(this::mapToDto);
    }
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }
    //  Save product
    @Override
    public Product save(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return productRepository.save(product);
    }//  Delete product by id
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );productRepository.delete(product);
    }//  Update product (BEST PRACTICE)
    @Override
    public Product updateProduct(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return productRepository.save(product);
    }
}