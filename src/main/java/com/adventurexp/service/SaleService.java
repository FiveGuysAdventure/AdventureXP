package com.adventurexp.service;


import com.adventurexp.model.Product;
import com.adventurexp.model.Sale;
import com.adventurexp.repository.ProductRepo;
import com.adventurexp.repository.SaleRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaleService {

    private final SaleRepo saleRepo;
    private final ProductRepo productRepo;

    public SaleService(SaleRepo saleRepo, ProductRepo productRepo) {
        this.saleRepo = saleRepo;
        this.productRepo = productRepo;
    }

    public Sale sellProduct(Integer productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("No product added");
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        Sale sale = new Sale(product, quantity, product.getPrice(), LocalDateTime.now());
        return saleRepo.save(sale);
    }
}
