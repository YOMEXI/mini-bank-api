package com.example.bank.swagger.utils;


import com.example.bank.auth.dto.createRole;
import com.example.bank.auth.dto.roleResponse;
import com.example.bank.auth.entities.RoleEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Role",description = "role api")
public interface RoleSwagger {

    @Operation(
            summary = "creates a role",
            description = "creates a single role ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "an object of role is returned")
    })
    roleResponse createRole(createRole role);

    @Operation(
            summary = " all roles",
            description = "Return all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a list of all roles")
    })
    List<roleResponse> allRoles();


    @Operation(
            summary = " a single role",
            description = "Return an object of role types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return an object of role type")
    })
    RoleEntity oneRole(String id);
}
