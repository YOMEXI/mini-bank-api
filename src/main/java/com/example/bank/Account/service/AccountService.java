package com.example.bank.Account.service;

import com.example.bank.Account.dto.SingleAccountDetailsDto;


import java.util.Optional;

public interface AccountService {
    Optional<SingleAccountDetailsDto> singleAccountDetails (String accountNumber);
}
