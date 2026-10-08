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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    //Finder alle produkter
    @Test
    void getAllProducts_shouldReturnAllProducts() {
        List<Product> products = List.of(new Product("Cola", 20),
                new Product("Haribo", 15));
        when(productRepo.findAll()).thenReturn(products);

        assertThat(productService.getAllProducts()).isSameAs(products);
    }

    //Finder alle aktive produkter som er til salg
    @Test
    void getActiveProducts_shouldReturnActiveProducts() {
        List<Product> activeProducts = List.of(new Product("Cola", 20),
                new Product("Haribo", 15));
        when(productRepo.findAllByActiveTrue()).thenReturn(activeProducts);

        assertThat(productService.getActiveProducts()).isSameAs(activeProducts);
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

    //SAD PATH: Medarbejderen glemmer at udfylde navn - der vises en fejl og intet gemmes
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

    //HAPPY PATH: Medarbejderen ændrer prisen på et produkt - ny pris gemmes
    @Test
    void updatePrice_shouldUpdateAndReturnProduct() {
        Product product = new Product("Candyfloss", 40);
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(productRepo.save(product)).thenReturn(product);

        Product result = productService.updatePrice(1, 30);

        assertThat(result.getPrice()).isEqualTo(30);
        verify(productRepo).save(product);
    }

    //SAD PATH: Medarbejderen angiver ny pris på 0 - der vises en fejl og intet gemmes
    @Test
    void updatePrice_throwErrorIfPriceIsZero() {
        assertThatThrownBy(() -> productService.updatePrice(1, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price must be greater than 0");

        verify(productRepo, never()).save(any());
    }

    //SAD PATH: Medarbejderen ændrer pris på ikke eksisterende produkt - der vises en fejl og intet gemmes
    @Test
    void updatePrice_throwErrorIfProductNotFound() {
        when(productRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.updatePrice(99, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with id 99 not found");

        verify(productRepo, never()).save(any());
    }

    //HAPPY PATH: Medarbejderen sletter et produkt - produktet sættes om inaktivt og gemmes
    @Test
    void deactivateProduct_ShouldSetActiveToFalse() {
        Product product = new Product("Popcorn", 30);
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(productRepo.save(product)).thenReturn(product);

        Product result = productService.deactivateProduct(1);

        assertThat(result.isActive()).isFalse();
        verify(productRepo).save(product);
    }

    //SAD PATH: Medarbejderen sletter et produkt der ikke eksisterer - der vises en fejl og intet gemmes
    @Test
    void deactivateProduct_throwErrorIfProductNotFound() {
        when(productRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.deactivateProduct(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with id 99 not found");

        verify(productRepo, never()).save(any());
    }
}
