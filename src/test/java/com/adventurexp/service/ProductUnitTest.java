package com.adventurexp.service;


import com.adventurexp.model.Product;
import com.adventurexp.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {

    @Mock private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts_shouldReturnAllProducts() {
        List<Product> products = List.of(new Product("Cola", 20), new Product("Haribo", 15));
        when(productRepo.findAll()).thenReturn(products);

        assertThat(productService.getAllProducts()).isSameAs(products);
    }

    //HAPPY PATH: Medarbejderen opretter et nyt produkt - det gemmes og returneres
    @Test
    void createProduct_shouldSaveAndReturnProduct() {
        Product product = new Product("Chips", 35);
        when(productRepo.existsByProductNameIgnoreCase("Chips")).thenReturn(false);
        when(productRepo.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertThat(result.getProductName()).isEqualTo("Chips");
        assertThat(result.getPrice()).isEqualTo(35);
        verify(productRepo).save(product);
    }

    //SAD PATH: Medarbejderen glemmer at udfylde evt. navn (har ikke lavet på pris som 0 eller negativ) - der vises en fejl og intet gemmes
    @Test
    void createProduct_throwErrorIfNameIsEmpty() {
        Product product = new Product("", 10);

        assertThatThrownBy(() -> productService.createProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product name may not be empty");

        verify(productRepo, never()).save(any());
    }

    //SAD PATH: Medarbejderen angiver en pris på 0 - der vises en fejl og intet gemmes
    @Test
    void createProduct_throwErrorIfPriceIsZero() {
        Product product = new Product("Is", 0);

        assertThatThrownBy(() -> productService.createProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price must be greater than 0");

        verify(productRepo, never()).save(any());
    }

    //SAD PATH: Medarbejderen opretter et eksisterende produkt - der vises en fejl og intet gemmes
    @Test
    void createProduct_throwErrorIfProductAlreadyExists() {
        Product product = new Product("Cola", 25);
        when(productRepo.existsByProductNameIgnoreCase("Cola")).thenReturn(true);

        assertThatThrownBy(() -> productService.createProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product Cola already exists");

        verify(productRepo, never()).save(any());
    }
}
