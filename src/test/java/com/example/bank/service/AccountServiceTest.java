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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
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
    private Transaction transactionWithdrawal;

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
        transactionDeposit.setReferenceNumber("100");
        transactionDeposit.setDescription("");

        transactionWithdrawal = new Transaction();
        transactionWithdrawal.setId(2L);
        transactionWithdrawal.setAccount(account);
        transactionWithdrawal.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionWithdrawal.setAmount(BigDecimal.valueOf(500));
        transactionWithdrawal.setTransactionType(TransactionType.WITHDRAWAL);
        transactionWithdrawal.setReferenceNumber("200");
        transactionWithdrawal.setDescription("");

    }

    @Test
    @DisplayName("should return a customer from the database")
    public void singleAccountDetails(){

        String accountNumber = "123456789";

        when(helperMethods.validateAccountWithAccountNumber(accountNumber)).thenReturn(account);

        SingleAccountDetailsDto accountDetails = accountService
                .singleAccountDetails(accountNumber);


        assertNotNull(accountDetails);
       assertThat(accountDetails.getAccountNumber()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("should deposit amount into user account")
    public void depositMoneyIntoUserAccount(){
        double amount = 300.00;
        String accountNumber = "123456789";


        when(helperMethods.validateAccountWithAccountNumber("1234567890")).thenReturn(account);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionDeposit);

        String  depositMoney = accountService.depositIntoAccount("1234567890",amount);
        assertNotNull(depositMoney);
    }

    @Test
    @DisplayName("should withdraw amount from user account")
    public void WithDrawMoneyFromUserAccount(){
        double amount = 300.00;
        String accountNumber = "123456789";

        when(helperMethods.validateAccountWithAccountNumber("1234567890")).thenReturn(account);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionWithdrawal);
        String  depositMoney = accountService.WithDrawFromAccount("1234567890",amount);
        assertNotNull(depositMoney);
    }

    @Test
    @DisplayName("should suspend user account")
    public void suspendUserAccount(){

        when(helperMethods.validateAccountWithAccountNumber("1234567890")).thenReturn(account);

        String  suspendedAccount = accountService.suspendAccount("1234567890");
        assertNotNull(suspendedAccount);
    }
}
