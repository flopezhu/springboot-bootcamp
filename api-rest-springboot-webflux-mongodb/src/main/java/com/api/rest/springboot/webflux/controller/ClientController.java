package com.api.rest.springboot.webflux.controller;

import com.api.rest.springboot.webflux.documents.Client;
import com.api.rest.springboot.webflux.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Value("${config.uploads.path}")
    private String path;

    @PostMapping("/registerClient")
    public Mono<ResponseEntity<Client>> registerClient(Client client, @RequestPart FilePart filePart) {
        client.setPhoto(UUID.randomUUID().toString() + "_" + filePart.filename()
                .replace(" ", "")
                .replace(":", "")
                .replace("//", ""));
        //client.getPhoto();
        return filePart.transferTo(new File(path + client.getPhoto()))
                .then(clientService.save(client))
                .map(clients -> ResponseEntity.created(URI.create("/api/clients".concat(clients.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(clients));
    }

    @PostMapping("/upload/{id}")
    public Mono<ResponseEntity<Client>> uploadPhoto(@PathVariable String id, @RequestPart FilePart filePart) {
        return clientService.findById(id).flatMap(clients -> {
                    clients.setPhoto(UUID.randomUUID().toString() + "_" + filePart.filename()
                            .replace(" ", "")
                            .replace(":", "")
                            .replace("//", ""));

                    return filePart.transferTo(new File(path + clients.getPhoto()))
                            .then(clientService.save(clients));
                }).map(clients -> ResponseEntity.ok(clients))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Client>>> listClients() {  //mono porque me devuelve solo una respuesta
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> getClientById(@PathVariable String id) {
        //map sirve para transformar la data
        return clientService.findById(id).map(clients -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clients))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveClient(@Valid @RequestBody Mono<Client> clientMono) {
        Map<String, Object> response = new HashMap<>();

        return clientMono.flatMap(clients -> {
            return clientService.save(clients).map(clientMap -> {
                response.put("client", clientMap);
                response.put("message", "client save successful");
                response.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/api/clients".concat(clientMap.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response);
            });
        });
    }
}
