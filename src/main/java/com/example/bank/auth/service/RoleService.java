package com.example.bank.auth.service;

import com.example.bank.auth.entities.RoleEntity;
import com.example.bank.auth.dto.createRole;
import com.example.bank.auth.dto.roleResponse;

import java.util.List;


public interface RoleService {
    roleResponse createRole(createRole role);
    List<roleResponse> allRoles();

    RoleEntity oneRole(String role);
}
