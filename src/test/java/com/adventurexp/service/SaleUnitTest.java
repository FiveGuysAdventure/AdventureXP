package com.adventurexp.service;


import com.adventurexp.model.Product;
import com.adventurexp.model.Sale;
import com.adventurexp.repository.ProductRepo;
import com.adventurexp.repository.SaleRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SaleUnitTest {

    @Mock
    private ProductRepo productRepo;
    @Mock
    private SaleRepo saleRepo;

    @InjectMocks
    private SaleService saleService;

    //HAPPY PATH: Medarbejderen sælger et eller flere produkter - salget gemmes
    @Test
    void sellProduct_shouldSaveSale() {
        Product product1 = new Product("Fanta", 35);
        when(productRepo.findById(1)).thenReturn(Optional.of(product1));

        saleService.sellProduct(1, 2);

        verify(productRepo).findById(1);
        verify(saleRepo).save(any(Sale.class));
    }

    //SAD PATH: Medarbejderen sælger uden at vælge antal produkter - der vises en fejl og intet gemmes
    @Test
    void sellProduct_throwErrorIfQuantityIsZero() {
        assertThatThrownBy(() -> saleService.sellProduct(1, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No product added");

        verify(saleRepo, never()).save(any());
    }

    //SAD PATH: Medarbejderen sælger et produkt der ikke findes - der vises en fejl og intet gemmes
    @Test
    void sellProduct_throwErrorIfProductNotFound() {
        when(productRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> saleService.sellProduct(99, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with id 99 not found");

        verify(saleRepo, never()).save(any());
    }
}
