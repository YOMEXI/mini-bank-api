package com.example.bank.service;


import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.enums.Currency;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Account.service.impl.AccountImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountImpl accountService;
    @Mock
    AccountRepository accountRepository;
    @Spy
    ModelMapper modelMapper;

    private Account account;
    @BeforeEach
    void init(){

        account = new Account();
        account.setAccountNumber("1234567890");
        account.setId(1);
        account.setAccountType(AccountType.Savings);
        account.setAccountStatus(AccountStatus.Active);
        account.setBalance(BigDecimal.valueOf(0.00));
        account.setClosed(false);
        account.setCurrency(Currency.NGN);
        account.setOpenDate(LocalDate.now());
        account.setClosedDate("");
        account.setTransactions(new ArrayList<>());
    }

    @Test
    @DisplayName("should return a customer from the database")
    public void singleAccountDetails(){

        String accountNumber = "123456789";

        when(accountRepository.findByAccountNumber(anyString())).thenReturn(Optional.of(account));

        Optional<SingleAccountDetailsDto> accountDetails = accountService
                .singleAccountDetails(account.getAccountNumber());

        assertNotNull(accountDetails);
       assertThat(accountDetails.get().getAccountNumber()).isNotEqualTo(null);


    }
}
