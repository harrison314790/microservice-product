package com.microservice.product.unit_tests;

import com.microservice.product.entities.Product;
import com.microservice.product.persistence.ProductRepository;
import com.microservice.product.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(99.99)
                .category("Test Category")
                .subcategory("Test Subcategory")
                .build();
    }

    @Test
    void findById_ShouldReturnProduct_WhenProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.findById(1L);

        assertEquals(product, found);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldReturnNull_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product found = productService.findById(1L);

        assertNull(found);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.findAll();

        assertEquals(1, foundProducts.size());
        assertEquals(product, foundProducts.get(0));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void save_ShouldReturnSavedProduct() {
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteById_ShouldDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}