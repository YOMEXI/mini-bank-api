package com.example.bank.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class employeeLoginResponse {
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    private String accessToken;
}
