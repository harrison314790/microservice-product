package com.microservice.product.persistence;

import com.microservice.product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    //List<Product>  findAllByProduct(Long idProduct)
}
