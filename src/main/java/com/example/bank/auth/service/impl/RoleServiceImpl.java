package com.example.bank.auth.service.impl;

import com.example.bank.Exception.CustomApiException;
import com.example.bank.auth.entities.RoleEntity;
import com.example.bank.auth.dto.createRole;
import com.example.bank.auth.dto.roleResponse;
import com.example.bank.auth.repository.RoleRepository;
import com.example.bank.auth.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public roleResponse createRole(createRole role) {

        Optional<RoleEntity> ifRoleExist = roleRepository.findByName(role.getName());

        if (ifRoleExist.isPresent())
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Role Already Exists");


        RoleEntity newRole = new RoleEntity();
        newRole.setName(role.getName());


        roleRepository.save(newRole);
        return modelMapper.map(newRole,roleResponse.class);
    }

    @Override
    public  List<roleResponse> allRoles() {

        List<RoleEntity> allFaculties = roleRepository.findAll();

        return allFaculties.stream()
                .map(role -> modelMapper.map(role, roleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleEntity oneRole(String id) {

        return roleRepository.findById(Integer.parseInt(id))
                .orElseThrow(()->new CustomApiException(
                        HttpStatus.BAD_REQUEST,"Role doesnt exist"
                ));
    }


}
