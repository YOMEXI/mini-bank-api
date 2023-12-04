package com.example.bank.auth.repository;

import com.example.bank.auth.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {


    Optional<RoleEntity> findByName(String name);
    //Optional<RoleEntity> findByRoleId(Integer roleId);
}

