package com.example.bank.Account.service.impl;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.dto.newAccountDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.Account.helperMethods.helperAccountMethods;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Account.service.AccountService;
import com.example.bank.Exception.CustomApiException;
import com.example.bank.User.Customer.service.impl.CustomerImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountImpl.class);

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<SingleAccountDetailsDto> singleAccountDetails(String accountNumber) {
        logger.info("newCustomer service class for single Account details");

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);

        if (account.isEmpty())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account with this account Number doesn't exist");

        return account.map(acc -> modelMapper.map(acc, SingleAccountDetailsDto.class));
    }

    public String depositIntoAccount(){
        logger.info("newCustomer service class for single Account details");

        return "";
    }
}
