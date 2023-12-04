package com.example.bank.Account.controller;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.service.impl.AccountImpl;
import com.example.bank.swagger.utils.AccountSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/account")
@RestController
public class AccountController implements AccountSwagger {

    @Autowired
    AccountImpl accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/{accountNumber}")
    public SingleAccountDetailsDto singleAccountDetails (@PathVariable String accountNumber){
        logger.info("Received request to a single account detail /api/v1/account");

        return  accountService.singleAccountDetails(accountNumber);

    }


    @PostMapping("/{accountNumber}/deposit")
    public String DepositIntoAccount(@PathVariable String accountNumber, @RequestParam double amount){
        logger.info("Received request to a deposit into account detail with account no: {}", accountNumber);

        return accountService.depositIntoAccount(accountNumber,amount);

    }

    @PostMapping("/{accountNumber}/withdrawal")
    public String withDrawFromAccount(@PathVariable String accountNumber, @RequestParam double amount){
        logger.info("Received request to a deposit into account detail with account no: {}", accountNumber);

        return accountService.WithDrawFromAccount(accountNumber,amount);

    }

    @PostMapping("/{accountNumber}/suspend")
    public String suspendAccount(@PathVariable String accountNumber){
        logger.info("Received request to a suspend an account detail with account no: {}", accountNumber);

        return accountService.suspendAccount(accountNumber);

    }
}
