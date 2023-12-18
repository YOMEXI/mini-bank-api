package com.example.bank.User.Customer.controller;

import com.example.bank.User.Customer.dto.newStaffDto;
import com.example.bank.User.Customer.dto.staffDto;
import com.example.bank.User.Customer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/staff")
public class EmployeeController {

    @Autowired
    EmployeeService staffService;

    @PostMapping()
    public staffDto createStaff (@Validated @RequestBody newStaffDto staff){


        return staffService.newStaff(staff);
    }
}
