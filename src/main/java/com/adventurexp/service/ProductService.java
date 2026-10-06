package com.adventurexp.service;

import com.adventurexp.model.Product;
import com.adventurexp.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("No product found");
        }

        String name = product.getProductName();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name may not be empty");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (productRepo.existsByProductNameIgnoreCase(name)) {
            throw new IllegalArgumentException("Product " + name + " already exists");
        }

        return productRepo.save(product);
    }

    public Product updatePrice(Integer productId, int newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        product.setPrice(newPrice);
        return productRepo.save(product);
    }
}
