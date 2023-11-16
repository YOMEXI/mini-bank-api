package com.example.bank.User.Customer.service;

import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.User.Customer.dto.newCustomerDto;

public interface CustomerService {
    newCustomerDto newCustomer(createCustomerDto customer);
}
