package com.microservice.product.service;

import com.microservice.product.entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> finAll();

    Product findById(long id);

    void save(Product product);

    //List<Product> finById***(Long id**+)
}
