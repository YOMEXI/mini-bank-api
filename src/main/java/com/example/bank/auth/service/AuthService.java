package com.example.bank.auth.service;

import com.example.bank.auth.dto.loginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> staffLogin(loginRequest request);
}
