package com.example.bank.auth.service.impl;

import com.example.bank.Exception.CustomApiException;
import com.example.bank.User.Customer.repository.EmployeeRepository;
import com.example.bank.User.Customer.service.impl.EmployeeServiceImpl;
import com.example.bank.auth.config.JwtFullService;
import com.example.bank.auth.dto.employeeLoginResponse;
import com.example.bank.auth.dto.loginRequest;
import com.example.bank.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
     JwtFullService jwtService;


    @Override
    public ResponseEntity<?> staffLogin(loginRequest request) {
        logger.info("Staff service class for Logging in with email: {}", request.getEmail());


        var employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new CustomApiException(
                        HttpStatus.BAD_REQUEST,"Employee does not exist"
                ));

        boolean comparePassword = passwordEncoder.matches(request.getPassword()
                ,employee.getPassword());


        if(!comparePassword)
            throw new CustomApiException(HttpStatus.BAD_REQUEST,"Bad credentials inputted");


        var authentication =  authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );




        List<String> role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        String jwtToken = jwtService.generateToken(employee);

        return ResponseEntity.ok(new employeeLoginResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                role,
                jwtToken
        ));
    }
}
