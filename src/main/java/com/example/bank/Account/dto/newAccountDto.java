package com.example.bank.Account.dto;

import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class newAccountDto {


    private Currency currency;

    private BigDecimal interestRate; // Applicable interest rate (for savings accounts, loans, etc.)
    private BigDecimal balance; // Applicable interest rate (for savings accounts, loans, etc.)
    private LocalDate openDate;
    private String nationalIdentityNumber;


    private boolean isActive; // Account status (active or inactive)
    private boolean isClosed; // Indicates whether the account is closed
    private LocalDate closeDate; // Date the account was closed


    @Enumerated(EnumType.STRING)
    private AccountType accountType; // Enum: SAVINGS, CHECKING, LOAN, etc.


    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}
