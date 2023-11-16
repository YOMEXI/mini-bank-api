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
    helperAccountMethods helperAccountMethods;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Account createAccountDetails(newAccountDto acc) {

        logger.info("createAccountDetails service class for creating customer");

        var accountNumber = helperAccountMethods.generateAccountNumber();

        Optional<Account> ifAccountExist = accountRepository.findByAccountNumber(accountNumber);


        if (ifAccountExist.isPresent())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account with this account Number Already exist Try again");

        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountStatus(acc.getAccountStatus());
        account.setAccountType(acc.getAccountType());
        account.setOpenDate(acc.getOpenDate());
        account.setTransactions(new ArrayList<>());
        account.setBalance(acc.getBalance());


        return accountRepository.save(account);
    }

    @Override
    public Optional<SingleAccountDetailsDto> singleAccountDetails(String accountNumber) {

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);

        if (account.isEmpty())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account with this account Number doesnt exist");

        return account.map(acc -> modelMapper.map(acc, SingleAccountDetailsDto.class));
    }
}
