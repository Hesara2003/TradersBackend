package com.example.hardwaremanagement.repository;

import com.example.hardwaremanagement.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Find products by category excluding the current product
    List<Product> findByCategoryAndIdNot(String category, String excludeId);
    
    // Find products by brand excluding the current product  
    List<Product> findByBrandAndIdNot(String brand, String excludeId);
    
    // Find products by category
    List<Product> findByCategory(String category);
    
    // Find products that are available (in stock)
    @Query("{ 'stock' : { $gt : 0 } }")
    List<Product> findAvailableProducts();
}
