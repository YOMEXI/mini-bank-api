package com.example.bank.auth.controller;

import com.example.bank.auth.entities.RoleEntity;
import com.example.bank.auth.dto.createRole;
import com.example.bank.auth.dto.roleResponse;
import com.example.bank.auth.service.RoleService;
import com.example.bank.swagger.utils.RoleSwagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/role")
public class RoleController  implements RoleSwagger {

    @Autowired
    RoleService roleService;


    @PostMapping()
    public roleResponse createRole(@RequestBody createRole role){
        return roleService.createRole(role);

    }

    @GetMapping()
    public List<roleResponse> allRoles(){
        return roleService.allRoles();

    }

    @GetMapping("{id}")
    public RoleEntity oneRole(@PathVariable String  id){
        return roleService.oneRole(id);

    }
}