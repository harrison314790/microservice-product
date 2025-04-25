package com.microservice.product.service;

import com.microservice.product.entities.Product;
import com.microservice.product.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    private ProductRepository productRepository;

    @Override
    public List<Product> finAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
