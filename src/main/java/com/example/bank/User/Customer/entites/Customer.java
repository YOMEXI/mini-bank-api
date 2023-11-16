package com.example.bank.User.Customer.entites;


import com.example.bank.Account.entities.Account;
import com.example.bank.User.Customer.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", schema = "bank")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    // Personal Information
    @Column(nullable = false)
    @NotBlank(message = "first name is mandatory")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


    @Column(nullable = false)
    @NotBlank(message = "SSN is mandatory")
    private String socialSecurityNumber;

    @Column(nullable = false)
    @NotBlank(message = "National Identity Number is mandatory")
    private String nationalIdentityNumber;

    @Column(nullable = false)
    @NotBlank(message = "email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "insert a valid email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "phone is mandatory")
    private String phone;

    @Column(nullable = false)
    @NotBlank(message = "address is mandatory")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "country is mandatory")
    private String nationality;

    @Column(nullable = false)
    @NotBlank(message = "city is mandatory")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "state is mandatory")
    private String state;

    private String postalCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_accounts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> account;

}
