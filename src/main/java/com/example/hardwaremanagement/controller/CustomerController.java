package com.example.hardwaremanagement.controller;

import com.example.hardwaremanagement.dto.ProductDTO;
import com.example.hardwaremanagement.dto.ProductDetailDTO;
import com.example.hardwaremanagement.model.Product;
import com.example.hardwaremanagement.service.ProductService;
import com.example.hardwaremanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;

    // Get all products as DTOs
    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return userService.getAllProductsDTO();
    }

    @GetMapping
    public List<ProductDTO> getAllProductsAlternative() {
        return userService.getAllProductsDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable String id) {
        ProductDetailDTO productDetails = productService.getProductDetails(id);
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping("/{id}/basic")
    public ResponseEntity<Product> getBasicProductInfo(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
