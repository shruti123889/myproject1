package com.example.airbnbproject.controller;
import com.example.airbnbproject.entity.Product;
import com.example.airbnbproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import jakarta.validation.Valid;
import com.example.airbnbproject.dto.ProductRequestDto;
import com.example.airbnbproject.dto.ProductDto;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public Product saveProduct(
            @Valid @RequestBody ProductRequestDto productDto) {
        return productService.save(productDto);}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }@PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProductDto updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto productDto) {
        Product product = productService.updateProduct(id, productDto);
        return mapToDto(product);}
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());   // ✅ yahi correct hai
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;}
    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String name) {

        return productService.getProducts(page, size, name);
    }
    @GetMapping("/")
    public String home() {
        return "Backend is running successfully 🚀";
    }
}