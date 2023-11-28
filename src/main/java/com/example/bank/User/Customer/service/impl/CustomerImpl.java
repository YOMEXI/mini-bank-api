package com.example.bank.User.Customer.service.impl;

import com.example.bank.Account.helperMethods.helperAccountMethods;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Exception.CustomApiException;
import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.User.Customer.entites.Customer;
import com.example.bank.User.Customer.dto.newCustomerDto;
import com.example.bank.User.Customer.repository.CustomerRepository;
import com.example.bank.Account.service.AccountService;
import com.example.bank.User.Customer.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerImpl.class);

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    helperAccountMethods helperAccountMethods;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public newCustomerDto newCustomer(createCustomerDto customer) {
        logger.info("newCustomer service class for creating customer: {}", customer.getFirstName());

        var newCustomer = new Customer();
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setMiddleName(customer.getMiddleName());
        newCustomer.setNationalIdentityNumber(customer.getNationalIdentityNumber());
        newCustomer.setCity(customer.getCity());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setDateOfBirth(customer.getDateOfBirth());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setGender(customer.getGender());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setNationality(customer.getNationality());
        newCustomer.setPostalCode(customer.getPostalCode());
        newCustomer.setSocialSecurityNumber(customer.getSocialSecurityNumber());
        newCustomer.setState(customer.getState());


        var accountNumber = helperAccountMethods.generateAccountNumber();

        Optional<Account> ifAccountExist = accountRepository.findByAccountNumber(accountNumber);

        if (ifAccountExist.isPresent())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account with this account Number Already exist Try again");

        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountStatus(customer.getAccount().getAccountStatus());
        account.setAccountType(customer.getAccount().getAccountType());
        account.setOpenDate(customer.getAccount().getOpenDate());
        account.setTransactions(new ArrayList<>());
        account.setBalance(BigDecimal.valueOf(0));

       var accDetails = accountRepository.save(account);
        if(accDetails.getId()== null) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST,"Error creating Account");
        }

        List <Account> newAccountList = new ArrayList<>();
        newAccountList.add(accDetails);
        newCustomer.setAccount(newAccountList);

        customerRepository.save(newCustomer);

        return modelMapper.map(newCustomer, newCustomerDto.class);
    }
}
