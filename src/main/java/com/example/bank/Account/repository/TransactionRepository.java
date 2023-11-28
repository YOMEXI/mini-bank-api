package com.example.bank.Account.repository;

import com.example.bank.Account.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository <Transaction,Integer> {
}
