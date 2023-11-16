package com.example.bank.Account.dto;

import com.example.bank.Account.entities.Transaction;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.enums.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SingleAccountDetailsDto {


    private Long  id;
    private String accountNumber;
    private BigDecimal balance;


    private Currency currency ;


    private LocalDate openDate;

    private List<Transaction> transactions;

    private boolean isClosed = false; // Indicates whether the account is closed
    private String closedDate ; // Date the account was closed


    private AccountType accountType; // Enum: SAVINGS, CHECKING, LOAN, etc.


    private AccountStatus accountStatus;
}
