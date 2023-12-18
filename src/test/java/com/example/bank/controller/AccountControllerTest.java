package com.example.bank.controller;

import com.example.bank.Account.controller.AccountController;
import com.example.bank.Account.dto.SingleAccountDetailsDto;
import com.example.bank.Account.entities.Account;
import com.example.bank.Account.enums.AccountStatus;
import com.example.bank.Account.enums.AccountType;
import com.example.bank.Account.enums.Currency;
import com.example.bank.Account.service.impl.AccountImpl;
import com.example.bank.User.Customer.controller.CustomerController;
import com.example.bank.auth.config.JwtAuthFilter;
import com.example.bank.auth.config.JwtFullService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@WebMvcTest(AccountController.class) // Provide your main application class
@ActiveProfiles("test")
public class AccountControllerTest {

    @MockBean
    private JwtFullService jwtService; // Mocked service

    @Autowired
    private JwtAuthFilter JwtAuthFilter;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountImpl accountService;

    @Autowired
    private ObjectMapper objectMapper;

    private Account account;
    @BeforeEach
    void init() {
        account = new Account();
        account.setAccountNumber("1234567890");
        account.setId(1);
        account.setAccountType(AccountType.Savings);
        account.setAccountStatus(AccountStatus.Active);
        account.setBalance(BigDecimal.valueOf(0.00));
        account.setClosed(false);
        account.setCurrency(Currency.NGN);
        account.setOpenDate(LocalDate.now());
        account.setClosedDate("");
        account.setTransactions(new ArrayList<>());
    }

    private final String url = "/api/v1";
    @Test
    @DisplayName("should return a customer from the database")
    public void singleAccountDetails() throws Exception {
        var singleAccount = new SingleAccountDetailsDto();
        singleAccount.setAccountStatus(AccountStatus.Active);
        singleAccount.setAccountType(AccountType.Savings);
        singleAccount.setAccountNumber("1234567890");
        singleAccount.setBalance(BigDecimal.valueOf(1234));
        singleAccount.setCurrency(Currency.NGN);
        singleAccount.setClosedDate("");

        when(accountService.singleAccountDetails(any(String.class)))
                .thenReturn(singleAccount);

        mockMvc.perform(get(url + "/account/{accountNumber}","123456789" ))
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber", is(account.getAccountNumber())))
                .andExpect(jsonPath("$.accountStatus", is("Active")));
    }

    @Test
    @DisplayName("deposit money into user account")
    public void depositMoneyIntoAccount() throws Exception {
        double amount = 500;
        when(accountService.depositIntoAccount("1234567890",amount))
                .thenReturn("Deposit Successfully Made");

        var result = this.mockMvc.perform(post(url +"/account/{accountNumber}/deposit","1234567890")
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect((MockMvcResultMatchers.content().string("Deposit Successfully Made")));


       // String responseContent = result.andReturn().getResponse().getContentAsString();
       // System.out.println(responseContent);

    }

    @Test
    @DisplayName("withdraw money from user account")
    public void withDrawalFromAccount() throws Exception {
        double amount = 500;
        when(accountService.WithDrawFromAccount("1234567890",amount))
                .thenReturn("WithDrawal Successfully Made");

        var result = this.mockMvc.perform(post(url +"/account/{accountNumber}/withdrawal","1234567890")
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect((MockMvcResultMatchers.content().string("WithDrawal Successfully Made")));
    }

}
