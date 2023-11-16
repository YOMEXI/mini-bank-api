package com.example.bank.Account.service;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.dto.newAccountDto;
import com.example.bank.Account.entities.Account;

import java.util.Optional;

public interface AccountService {
    Account createAccountDetails(newAccountDto account);
    Optional<SingleAccountDetailsDto> singleAccountDetails (String accountNumber);
}
