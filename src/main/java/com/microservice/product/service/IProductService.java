package com.microservice.product.service;

import com.microservice.product.entities.Product;

import java.util.List;

public interface IProductService {


    Product findById(long id);

    List<Product> findAll(); // Consultar todos los productos

    Product save(Product product); // Crear o editar un producto

    void deleteById(long id);
}
