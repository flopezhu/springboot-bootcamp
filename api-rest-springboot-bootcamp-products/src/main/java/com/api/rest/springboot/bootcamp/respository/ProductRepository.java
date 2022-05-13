package com.api.rest.springboot.bootcamp.respository;

import com.api.rest.springboot.bootcamp.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
