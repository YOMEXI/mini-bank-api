package com.example.bank.Account.entities;


import com.example.bank.Account.enums.TransactionStatus;
import com.example.bank.Account.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction", schema = "bank")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private BigDecimal amount;

    private BigDecimal withDrawAmount;

    private BigDecimal DepositAmount;


    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    // Description
    private String description;

    // Transaction Reference Number
    private String referenceNumber; // A unique reference number for the transaction

    // Additional Information (e.g., for payment reference, check number, etc.)
    private String additionalInfo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

}
