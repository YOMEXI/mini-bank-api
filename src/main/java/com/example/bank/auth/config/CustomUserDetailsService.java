package com.example.bank.auth.config;

import com.example.bank.User.Customer.entites.Employees;
import com.example.bank.User.Customer.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employees> employeeeOptional  = employeeRepository.findByEmail(username);


        if (employeeeOptional.isPresent()){
            return  employeeeOptional.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
