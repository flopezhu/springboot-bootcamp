package com.api.rest.springboot.webflux.repository;

import com.api.rest.springboot.webflux.documents.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientDao extends ReactiveMongoRepository<Client, String> {

}
