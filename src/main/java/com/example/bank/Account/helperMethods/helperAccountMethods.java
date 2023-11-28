package com.example.bank.Account.helperMethods;

import com.example.bank.Account.entities.Account;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Exception.CustomApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Component
public class helperAccountMethods {
    @Autowired
    AccountRepository accountRepository;

    public String generateAccountNumber(){
        Random random = new Random();
        int randomPart = random.nextInt(900000000) + 1000000000;


        return String.valueOf(randomPart);
    }

    public void depositValidation(double amount,Optional<Account>account){

        if (account.get().getAccountStatus()== AccountStatus.Suspended)
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account Suspended Transaction cannot progress");

        final double minimumDeposit = 500.00;


        if(amount < minimumDeposit )
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Minimum amount deposit-able is 500 naira");


        //return true;
    }

    public void withDrawalValidation(Double amount, Optional<Account> account) {
        final double minimumWithDrawableAmount = 500.00;

        if (account.get().getAccountStatus()== AccountStatus.Suspended)
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account Suspended Transaction cannot progress");

        if (amount < minimumWithDrawableAmount)
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Minimum amount withdrawa-able is 500 naira");

        if (account.get().getBalance().compareTo(BigDecimal.valueOf(amount)) < 0)
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    " Insufficient Funds");



    }

    public Optional<Account> validateAccountWithAccountNumber(String accountNumber){

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);

        if (account.isEmpty())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Account with this account Number doesn't exist");

        return account;
    }
}
