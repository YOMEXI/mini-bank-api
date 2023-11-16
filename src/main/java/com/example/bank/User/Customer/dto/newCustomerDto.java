package com.example.bank.User.Customer.dto;

import com.example.bank.Account.entities.Account;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class newCustomerDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private List<Account> account;
    private LocalDate dateOfBirth;
}
