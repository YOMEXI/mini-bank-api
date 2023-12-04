package com.example.bank.Account.service.impl;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.entities.Transaction;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.TransactionStatus;
import com.example.bank.Account.enums.TransactionType;
import com.example.bank.Account.helperMethods.helperAccountMethods;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Account.repository.TransactionRepository;
import com.example.bank.Account.service.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountImpl.class);

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ModelMapper modelMapper;
   @Autowired
   helperAccountMethods helperMethods;
   @Autowired
    TransactionRepository transactionRepository;

    @Override
    public SingleAccountDetailsDto singleAccountDetails(String accountNumber) {
        logger.info("newCustomer service class for single Account details");

        var account = helperMethods.validateAccountWithAccountNumber(accountNumber);

        return  modelMapper.map(account, SingleAccountDetailsDto.class);
    }


    public String depositIntoAccount(String accountNumber, double amount){
        logger.info("service class for Deposit into account {}",accountNumber);

        var account = helperMethods.validateAccountWithAccountNumber(accountNumber);
        helperMethods.depositValidation(amount,account);

       var updatedBalance = account.getBalance().add(BigDecimal.valueOf(amount));

                   var transaction = new Transaction();
                   transaction.setAmount(updatedBalance);
                   transaction.setDepositAmount(BigDecimal.valueOf(amount));
                   transaction.setReferenceNumber(helperMethods.generateAccountNumber());
                   transaction.setWithDrawAmount(BigDecimal.valueOf(0));
                   transaction.setTransactionType(TransactionType.DEPOSIT);
                   transaction.setTransactionStatus(TransactionStatus.SUCCESS);
                   transaction.setAccount(account);

                   account.setBalance(updatedBalance);

                   accountRepository.save(account);
                   transactionRepository.save(transaction);

                   return "Deposit Successfully Made";
    }

    public String WithDrawFromAccount(String accountNumber, double amount){
        logger.info("service class for Withdrawal from an account {}",accountNumber);



        var account = helperMethods.validateAccountWithAccountNumber(accountNumber);
        helperMethods.withDrawalValidation(amount,account);

        var updatedBalance = account.getBalance().subtract(BigDecimal.valueOf(amount));



        var transaction = new Transaction();
        transaction.setAmount(updatedBalance);
        transaction.setDepositAmount(BigDecimal.valueOf(amount));
        transaction.setReferenceNumber(helperMethods.generateAccountNumber());
        transaction.setWithDrawAmount(BigDecimal.valueOf(0));
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setAccount(account);

        account.setBalance(updatedBalance);

        accountRepository.save(account);
        transactionRepository.save(transaction);

        return "WithDrawal Successfully Made";
    }

    public String suspendAccount(String accountNumber) {
        var account = helperMethods.validateAccountWithAccountNumber(accountNumber);

        account.setAccountStatus(AccountStatus.Suspended);
        accountRepository.save(account);

        return "Account Suspended";
    }

}
