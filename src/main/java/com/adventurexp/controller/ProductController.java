package com.adventurexp.controller;


import com.adventurexp.model.Product;
import com.adventurexp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getActiveProducts();
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.createProduct(product));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/products/{id}/price")
    public ResponseEntity<Product> updatePrice(@PathVariable Integer id, @RequestParam int price) {
        try {
            return ResponseEntity.ok(productService.updatePrice(id, price));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> deactivateProduct(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productService.deactivateProduct(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
