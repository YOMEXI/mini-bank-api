package com.example.bank.Account.controller;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.service.impl.AccountImpl;
import com.example.bank.swagger.utils.AccountSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/account")
@RestController
public class AccountController implements AccountSwagger {

    @Autowired
    AccountImpl accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/{accountNumber}")
    public Optional<SingleAccountDetailsDto> singleAccountDetails (@PathVariable String accountNumber){
        logger.info("Received request to a single account detail /api/v1/account");

        return  accountService.singleAccountDetails(accountNumber);

    }

    @GetMapping()
    public String Test (@PathVariable String accountNumber){
        logger.info("Received request to a single account detail /api/v1/account");

        return  "11111116666";

    }
}
