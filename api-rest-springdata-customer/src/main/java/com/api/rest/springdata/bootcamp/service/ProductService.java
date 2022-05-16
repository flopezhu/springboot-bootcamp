package com.api.rest.springdata.bootcamp.service;

import com.api.rest.springdata.bootcamp.document.Product;
import com.api.rest.springdata.bootcamp.dto.ProductDto;

public interface ProductService {
    ProductDto getAllProducts();

    ProductDto getProductForId(String id);

    ProductDto createNewProduct(ProductDto productDto);

    ProductDto updateProductForId(ProductDto productDto, String id);

    Void deleteProductForId(String id);
}
