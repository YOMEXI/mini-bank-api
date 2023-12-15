package com.example.bank.auth.dto;


import lombok.Data;

@Data
public class loginRequest {
    private String email;
    private String password;
}
