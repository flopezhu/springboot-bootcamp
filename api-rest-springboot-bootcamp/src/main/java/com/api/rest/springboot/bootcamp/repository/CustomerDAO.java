package com.api.rest.springboot.bootcamp.repository;

import com.api.rest.springboot.bootcamp.documents.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends ReactiveMongoRepository<Customer, String> {
}
