package com.api.rest.springdata.bootcamp.controller;

import com.api.rest.springdata.bootcamp.document.Customer;
import com.api.rest.springdata.bootcamp.dto.CustomerDto;
import com.api.rest.springdata.bootcamp.dto.CustomerResponseDto;
import com.api.rest.springdata.bootcamp.service.CustomerService;
import com.api.rest.springdata.bootcamp.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CustomerResponseDto> getAllCustomer(@RequestParam(value = "pageNumber", defaultValue = AppConstant.NUMBER_PAGE_FOR_DEFAULT, required = false) int pageNumber,
                                                              @RequestParam(value = "pageSize", defaultValue = AppConstant.SIZE_PAGE_FOR_DEFAULT, required = false) int pageSize) {
        return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));
    }
}
