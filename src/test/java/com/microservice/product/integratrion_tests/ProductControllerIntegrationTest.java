package com.microservice.product.integratrion_tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.product.entities.Product;
import com.microservice.product.persistence.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;
    private Product testProduct;

    @BeforeAll
    void setupAll() {
        baseUrl = "http://localhost:" + port + "/product";
    }

    @BeforeEach
    void setUp() {
        // Limpiar base de datos antes de cada test
        productRepository.deleteAll();

        testProduct = Product.builder()
                .name("Integration Test Product")
                .description("Integration Test Description")
                .price(49.99)
                .category("Integration Test Category")
                .subcategory("Integration Test Subcategory")
                .build();

        testProduct = productRepository.save(testProduct);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("GET /product/search/{id} - Success")
    void findById_ShouldReturnProduct_WhenProductExists() throws Exception {
        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
                baseUrl + "/search/" + testProduct.getId(), String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Product responseProduct = objectMapper.readValue(response.getBody(), Product.class);
        assertNotNull(responseProduct);
        assertEquals(testProduct.getId(), responseProduct.getId());
    }

    @Test
    @DisplayName("GET /product/search/{id} - Not Found")
    void findById_ShouldReturnNotFound_WhenProductDoesNotExist() {
        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
                baseUrl + "/search/999", String.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("POST /product/create - Success")
    void createProduct_ShouldReturnCreatedStatus() {
        // Arrange
        Product newProduct = Product.builder()
                .name("New Product")
                .description("New Description")
                .price(29.99)
                .category("New Category")
                .subcategory("New Subcategory")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> request = new HttpEntity<>(newProduct, headers);

        // Act
        ResponseEntity<Void> response = restTemplate.postForEntity(
                baseUrl + "/create", request, Void.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
    }

    @Test
    @DisplayName("GET /product/all - Success")
    void findAll_ShouldReturnAllProducts() throws Exception {
        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
                baseUrl + "/all", String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Product> products = objectMapper.readValue(
                response.getBody(),
                new TypeReference<List<Product>>() {});

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    @DisplayName("PUT /product/edit/{id} - Success")
    void updateProduct_ShouldReturnUpdatedProduct_WhenProductExists() {
        // Arrange
        Product updatedProduct = Product.builder()
                .name("Updated Product")
                .description("Updated Description")
                .price(59.99)
                .category("Updated Category")
                .subcategory("Updated Subcategory")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> request = new HttpEntity<>(updatedProduct, headers);

        // Act
        ResponseEntity<Product> response = restTemplate.exchange(
                baseUrl + "/edit/" + testProduct.getId(),
                HttpMethod.PUT,
                request,
                Product.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Product", response.getBody().getName());
    }

    @Test
    @DisplayName("DELETE /product/delete/{id} - Success")
    void deleteProduct_ShouldReturnNoContent_WhenProductExists() {
        // Act
        ResponseEntity<Void> response = restTemplate.exchange(
                baseUrl + "/delete/" + testProduct.getId(),
                HttpMethod.DELETE,
                null,
                Void.class);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}