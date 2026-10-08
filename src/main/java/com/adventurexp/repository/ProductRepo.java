package com.adventurexp.repository;

import com.adventurexp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    boolean existsByProductNameIgnoreCase(String productName);

    List<Product> findAllByActiveTrue();
}
