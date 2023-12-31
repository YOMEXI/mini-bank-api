package com.example.bank.User.Customer.controller;

import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.User.Customer.dto.newCustomerDto;
import com.example.bank.User.Customer.service.CustomerService;

import com.example.bank.swagger.utils.CustomerSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController implements CustomerSwagger {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public newCustomerDto createCustomer(@Validated @RequestBody createCustomerDto customer){
        logger.info("Received request to /api/v1/customer");

        return customerService.newCustomer(customer);
    }
}
