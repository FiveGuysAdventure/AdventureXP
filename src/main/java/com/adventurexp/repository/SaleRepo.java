package com.adventurexp.repository;

import com.adventurexp.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepo extends JpaRepository<Sale, Integer> {
}
