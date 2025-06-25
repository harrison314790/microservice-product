package com.microservice.product.unit_tests;

import com.microservice.product.controller.ProductController;
import com.microservice.product.entities.Product;
import com.microservice.product.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

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
        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldReturnNotFound_WhenProductDoesNotExist() {
        when(productService.findById(1L)).thenReturn(null);

        ResponseEntity<Product> response = productController.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void deleteProduct_ShouldReturnNoContent_WhenProductExists() {
        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_ShouldReturnNotFound_WhenProductDoesNotExist() {
        when(productService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, never()).deleteById(anyLong());
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct_WhenProductExists() {
        Product updatedProduct = Product.builder()
                .name("Updated Product")
                .description("Updated Description")
                .price(199.99)
                .category("Updated Category")
                .subcategory("Updated Subcategory")
                .build();

        when(productService.findById(1L)).thenReturn(product);
        when(productService.save(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Product", response.getBody().getName());
        verify(productService, times(1)).save(product);
    }

    @Test
    void findAllProduct_ShouldReturnAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productService.findAll()).thenReturn(products);

        ResponseEntity<?> response = productController.findAllProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).findAll();
    }
}