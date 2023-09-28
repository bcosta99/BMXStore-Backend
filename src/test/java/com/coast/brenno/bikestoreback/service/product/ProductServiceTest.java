package com.coast.brenno.bikestoreback.service.product;

import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("Cuando el producto tiene stock, debe retornar el producto")
    void givenProductWithStock_shouldReturnProduct() {
        // given (dados los siguientes datos iniciales)
        Product product = new Product();
        product.setStock(1);

        long productId = 1L;
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(product));

        // when (cuando ejecuto el metodo en cuestion)
        Product productWithStock = productService.getProductById(productId);

        // then (entonces compruebo que....)
        Assertions.assertNotNull(productWithStock);
    }

    @Test
    @DisplayName("Cuando el producto no tiene stock, debe lanzar una excepcion")
    void givenProductWithoutStock_shouldThrowException() {
        // given (dados los siguientes datos iniciales)
        Product product = new Product();
        product.setStock(0);

        long productId = 1L;
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(product));

        // when (cuando ejecuto el metodo en cuestion)
        // then (entonces compruebo que....)
        Assertions.assertThrows(
                RuntimeException.class,
                () -> productService.getProductById(productId)
        );
    }
}