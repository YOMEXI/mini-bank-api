package com.example.bank.service;

import com.example.bank.Account.dto.newAccountDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.helperMethods.helperAccountMethods;
import com.example.bank.Account.repository.AccountRepository;
import com.example.bank.Account.service.impl.AccountImpl;
import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.User.Customer.dto.newCustomerDto;
import com.example.bank.User.Customer.entites.Customer;
import com.example.bank.User.Customer.enums.Gender;
import com.example.bank.User.Customer.repository.CustomerRepository;
import com.example.bank.User.Customer.service.impl.CustomerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {


    @InjectMocks
    private CustomerImpl customerServiceMock;


    // Mock dependencies
    @Mock
    AccountRepository accountRepository;
    @Mock
    CustomerRepository customerRepository;
    @Spy
    ModelMapper modelMapper;
    @Mock
    helperAccountMethods helperAccountMethods;

    private Customer newCustomer;
    private Account newAccount;
    private newAccountDto accountDto;
    private newCustomerDto customerDto;

    @BeforeEach
    void init(){
        newCustomer = new Customer();
        newCustomer.setId(1);
        newCustomer.setFirstName("abayomi");
        newCustomer.setLastName("akin");
        newCustomer.setMiddleName("ola");
        newCustomer.setNationalIdentityNumber("ZX1000");
        newCustomer.setCity("abuja");
        newCustomer.setAddress("lagos street");
        newCustomer.setDateOfBirth(LocalDate.parse("2020-09-01"));
        newCustomer.setEmail("abayomexy@gmail.com");
        newCustomer.setGender(Gender.valueOf("Male"));
        newCustomer.setPhone("1111");
        newCustomer.setNationality("Nigeria");
        newCustomer.setPostalCode("900001");
        newCustomer.setSocialSecurityNumber("SSS111");
        newCustomer.setState("ondo");

        customerDto = new newCustomerDto();
        customerDto.setFirstName("abayomi");
        customerDto.setLastName("akin");
        customerDto.setMiddleName("ola");
        customerDto.setDateOfBirth(LocalDate.parse("2020-09-01"));
        customerDto.setEmail("abayomexy@gmail.com");
        customerDto.setPhone("1111");
        customerDto.setAccount(new ArrayList<>());

        newAccount = new Account();
        newAccount.setAccountNumber("1111111111");
        newAccount.setId(1);
        newAccount.setAccountType(AccountType.Savings);
        newAccount.setAccountStatus(AccountStatus.Active);
        newAccount.setTransactions(new ArrayList<>());

        accountDto = new newAccountDto();
        accountDto.setOpenDate(LocalDate.parse("2020-09-01"));
        accountDto.setAccountType(AccountType.Savings);
        accountDto.setAccountStatus(AccountStatus.Active);


    }

    @Test
    @DisplayName("should save the new customer with new account to the database")
   public void newCustomer(){
        newAccountDto account = new newAccountDto();
        account.setAccountType(AccountType.Savings);
        account.setAccountStatus(AccountStatus.Active);

        createCustomerDto customer = new createCustomerDto();
        customer.setFirstName("abayomi");
        customer.setLastName("akin");
        customer.setCity("ondo");
        customer.setGender(Gender.valueOf("Male"));
        customer.setAddress("lagos street");
        customer.setEmail("abayomexy@gmail.com");
        customer.setDateOfBirth(LocalDate.now());
        customer.setNationality("Nigeria");
        customer.setNationalIdentityNumber("ER367YU");
        customer.setPostalCode("900001");
        customer.setAccount(account);

        when(accountRepository.save(any(Account.class))).thenReturn(newAccount);
        when(helperAccountMethods.generateAccountNumber()).thenReturn("1234567");
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        var createdCustomer = customerServiceMock.newCustomer(customer);
        verify(helperAccountMethods,times(1)).generateAccountNumber();
        assertNotNull(createdCustomer);
    }

}
