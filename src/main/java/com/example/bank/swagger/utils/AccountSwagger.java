package com.example.bank.swagger.utils;

import com.example.bank.Account.dto.SingleAccountDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Tag(name = "Account",description = "Account api")
public interface AccountSwagger {

    @Operation(
            summary = "gets a single customer",
            description = "gets a single customer using the account number value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "an object of some properties of the new account")
    })

     Optional<SingleAccountDetailsDto> singleAccountDetails ( String accountNumber);
}
