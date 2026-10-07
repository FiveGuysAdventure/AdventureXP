package com.adventurexp.controller;


import com.adventurexp.model.Sale;
import com.adventurexp.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/api/sales")
    public ResponseEntity<Sale> sellProduct(@RequestParam Integer productId, @RequestParam int quantity) {
        try {
            return ResponseEntity.ok(saleService.sellProduct(productId, quantity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
