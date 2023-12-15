package com.example.bank.auth.controller;


import com.example.bank.auth.dto.loginRequest;
import com.example.bank.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/staff")
    public ResponseEntity<?> staffLogin (@RequestBody loginRequest request){
        return authService.staffLogin(request);
    }
}
