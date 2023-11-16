package com.example.bank.Account.entities;


import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.enums.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account", schema = "bank")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;

    @Column(unique = true)
    private String accountNumber; // A unique account number

    // Balance and Currency Information
   // @Column(precision = 19, scale = 2, columnDefinition = "0.00")
    @Column
    private BigDecimal balance = BigDecimal.valueOf(0.00);

    @Column(nullable = false)
    private Currency currency = Currency.NGN;

    @Column(nullable = false)
    @Past(message = "Date of birth must be from the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Transaction> transactions;

    private boolean isClosed = false; // Indicates whether the account is closed
    private String closedDate ; // Date the account was closed

    @Enumerated(EnumType.STRING)
    private AccountType accountType; // Enum: SAVINGS, CHECKING, LOAN, etc.

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


}
