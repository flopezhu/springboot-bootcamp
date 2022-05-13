package com.api.rest.springboot.bootcamp.service.impl;

import com.api.rest.springboot.bootcamp.document.Product;
import com.api.rest.springboot.bootcamp.dto.ProductDto;
import com.api.rest.springboot.bootcamp.respository.ProductRepository;
import com.api.rest.springboot.bootcamp.service.ProductService;
import com.api.rest.springboot.bootcamp.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> getProductForId(String id) {
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(AppUtils::dtoToEntities)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> updateProductForId(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntities))
                .doOnNext(idProduct -> idProduct.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<Void> deleteProductForId(String id) {
        return productRepository.deleteById(id);
    }


}
