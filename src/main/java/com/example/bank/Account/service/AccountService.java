package com.example.bank.Account.service;

import com.example.bank.Account.dto.SingleAccountDetailsDto;

public interface AccountService {
    SingleAccountDetailsDto singleAccountDetails (String accountNumber);
    String depositIntoAccount (String accountNumber, double deposit);
    String WithDrawFromAccount(String accountNumber, double amount);
    String suspendAccount(String accountNumber);
}
