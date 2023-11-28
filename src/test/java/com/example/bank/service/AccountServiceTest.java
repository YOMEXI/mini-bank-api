package com.example.bank.service;


import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.Account.entities.Transaction;
import com.example.bank.Account.enums.*;
import com.example.bank.Account.helperMethods.helperAccountMethods;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Account.repository.TransactionRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountImpl accountService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    TransactionRepository transactionRepository;
    @Spy
    ModelMapper modelMapper;

    @Mock
    helperAccountMethods helperMethods;

    private Account account;
    private Transaction transactionDeposit ;

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

        transactionDeposit = new Transaction();
        transactionDeposit.setId(1L);
        transactionDeposit.setAccount(account);
        transactionDeposit.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionDeposit.setAmount(BigDecimal.valueOf(500));
        transactionDeposit.setTransactionType(TransactionType.DEPOSIT);
        transactionDeposit.setReferenceNumber(anyString());
        transactionDeposit.setDescription("");

    }

    @Test
    @DisplayName("should return a customer from the database")
    public void singleAccountDetails(){

        String accountNumber = "123456789";

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        Optional<SingleAccountDetailsDto> accountDetails = accountService
                .singleAccountDetails(account.getAccountNumber());

        assertNotNull(accountDetails);
       assertThat(accountDetails.get().getAccountNumber()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("should deposit amount into user account")
    public void depositMoneyIntoUserAccount(){
        double amount = 300.00;

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.ofNullable(account));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionDeposit);
        String  depositMoney = accountService.depositIntoAccount("1234567890",amount);
        assertNotNull(depositMoney);
    }
}
