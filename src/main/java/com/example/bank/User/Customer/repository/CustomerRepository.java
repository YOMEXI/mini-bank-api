package com.example.bank.User.Customer.repository;

import com.example.bank.User.Customer.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
