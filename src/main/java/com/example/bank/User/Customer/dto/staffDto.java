package com.example.bank.User.Customer.dto;

import com.example.bank.User.Customer.enums.StaffPosition;
import com.example.bank.auth.entities.RoleEntity;
import lombok.Data;

import java.util.List;


@Data
public class staffDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private StaffPosition position;
    private String email;
    private boolean active;

    private List<RoleEntity> role;
}
