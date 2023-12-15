package com.example.bank.User.Customer.service.impl;

import com.example.bank.Exception.CustomApiException;
import com.example.bank.User.Customer.dto.newStaffDto;
import com.example.bank.User.Customer.dto.staffDto;
import com.example.bank.User.Customer.entites.Employees;
import com.example.bank.User.Customer.enums.StaffPosition;
import com.example.bank.User.Customer.repository.EmployeeRepository;
import com.example.bank.User.Customer.service.EmployeeService;
import com.example.bank.auth.constants.RolesConstant;
import com.example.bank.auth.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public staffDto newStaff(newStaffDto staff) {
        logger.info("Staff service class for creating new staff");

        if(employeeRepository.findByEmail(staff.getEmail()).isPresent())
            throw  new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Staff with email already Exist");

        var newStaff = new Employees();
        newStaff.setEmail(staff.getEmail());
        newStaff.setFirstName(staff.getFirstName());
        newStaff.setLastName(staff.getLastName());
        newStaff.setPosition(staff.getPosition());
        newStaff.setPassword(passwordEncoder.encode(staff.getPassword()));

        var role = roleRepository.findById(RolesConstant.staff_role_id)
                .orElseThrow(()-> new CustomApiException(HttpStatus.BAD_REQUEST,
                        "Role Does not exist"));

        System.out.println(role);
        newStaff.setRole(Collections.singleton(role));
       employeeRepository.save(newStaff);

        return modelMapper.map(newStaff, staffDto.class);
    }
}
