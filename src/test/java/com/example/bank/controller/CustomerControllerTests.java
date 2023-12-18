package com.example.bank.controller;

import com.example.bank.User.Customer.controller.CustomerController;
import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.User.Customer.dto.newCustomerDto;
import com.example.bank.User.Customer.entites.Customer;
import com.example.bank.User.Customer.enums.Gender;
import com.example.bank.User.Customer.service.impl.CustomerImpl;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

@AutoConfigureMockMvc
@WebMvcTest(CustomerController.class) // Provide your main application class
@ActiveProfiles("test")
public class CustomerControllerTests {
    @MockBean
    private JwtFullService jwtService; // Mocked service

    @Autowired
    private JwtAuthFilter JwtAuthFilter;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer newCustomer;
    private newCustomerDto customerDto;
    private createCustomerDto customer;

    @BeforeEach()
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


        customer = new createCustomerDto();
        customer.setFirstName("abayomi");
        customer.setLastName("akin");
        customer.setMiddleName("ola");
        customer.setDateOfBirth(LocalDate.parse("2020-09-01"));
        customer.setEmail("abayomexy@gmail.com");
        customer.setPhone("1111");
        customer.setNationality("Nigeria");
        customer.setNationalIdentityNumber("EDR344");
        customer.setAddress("lagos street");
        customer.setState("Abuja");
        customer.setCity("Kwali");

    }

    private final String url = "/api/v1";
    @Test
    @DisplayName("should create new customer")
    void shouldCreateNewMovie() throws Exception {

        when(customerService.newCustomer(any(createCustomerDto.class)))
                .thenReturn(customerDto);


        mockMvc.perform(post(url + "/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                //.andDo(print())
                .andExpect(jsonPath("$.lastName", is(customerDto.getLastName())));

    }
}
