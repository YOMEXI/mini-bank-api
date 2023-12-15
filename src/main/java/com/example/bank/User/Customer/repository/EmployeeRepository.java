package com.example.bank.User.Customer.repository;

import com.example.bank.User.Customer.entites.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
    Optional<Employees> findByEmail(String email);
}
