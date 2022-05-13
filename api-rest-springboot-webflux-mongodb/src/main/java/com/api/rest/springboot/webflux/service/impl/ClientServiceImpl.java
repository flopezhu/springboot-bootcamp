package com.api.rest.springboot.webflux.service.impl;

import com.api.rest.springboot.webflux.documents.Client;
import com.api.rest.springboot.webflux.repository.ClientDao;
import com.api.rest.springboot.webflux.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Flux<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientDao.findById(id);
    }

    @Override
    public Mono<Client> save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Mono<Void> delete(Client client) {
        return clientDao.delete(client);
    }
}
