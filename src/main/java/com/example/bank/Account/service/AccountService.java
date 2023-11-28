package com.example.bank.Account.service;

import com.example.bank.Account.dto.SingleAccountDetailsDto;


import java.util.Optional;

public interface AccountService {
    Optional<SingleAccountDetailsDto> singleAccountDetails (String accountNumber);
    String depositIntoAccount (String accountNumber, double deposit);
    String WithDrawFromAccount(String accountNumber, double amount);
    String suspendAccount(String accountNumber);
}
