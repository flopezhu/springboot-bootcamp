package com.api.rest.springboot.webflux.service;

import com.api.rest.springboot.webflux.documents.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();

    Mono<Client> findById(String id);

    Mono<Client> save(Client client);

    Mono<Void> delete(Client client);
}
