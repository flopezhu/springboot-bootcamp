package com.api.rest.springdata.bootcamp.service;

import com.api.rest.springdata.bootcamp.dto.CustomerTypeDto;

public interface CustomerTypeService {
    CustomerTypeDto getAllCustomerType();

    CustomerTypeDto getCustomerTypeForId(String id);

    CustomerTypeDto createNewCustomerType(CustomerTypeDto customerTypeDto);

    CustomerTypeDto updateCustomerTypeForId(CustomerTypeDto customerTypeDto, String id);

    Void deleteCustomerTypeForId(String id);
}
