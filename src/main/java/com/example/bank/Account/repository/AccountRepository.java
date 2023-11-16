package com.example.bank.Account.repository;

import com.example.bank.Account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccountNumber(String accNo);

}
