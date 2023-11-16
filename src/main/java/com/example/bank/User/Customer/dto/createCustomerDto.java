package com.example.bank.User.Customer.dto;


import com.example.bank.Account.dto.newAccountDto;
import com.example.bank.User.Customer.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class createCustomerDto {


    @NotBlank(message = "first name is mandatory")
    private String firstName;


    @NotBlank(message = "last name is mandatory")
    private String lastName;


    @NotBlank(message = "middle name is mandatory")
    private String middleName;


    @Past(message = "Date of birth must be from the past")
    private LocalDate dateOfBirth;

    private String socialSecurityNumber;


    @NotBlank(message = "National Identity Number is necessary")
    private String nationalIdentityNumber;


    @NotBlank(message = "email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "insert a valid email")
    private String email;


    @NotBlank(message = "Phone number is mandatory")
    private String phone;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "city Identity Number is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;


    private String postalCode;

    @NotBlank(message = "Country is mandatory")
    private String nationality;


    private Gender gender;

    private newAccountDto account;
}
