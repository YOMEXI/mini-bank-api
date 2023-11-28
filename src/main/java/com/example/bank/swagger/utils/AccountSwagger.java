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

    @Operation(
            summary = "Deposit amount into customer account",
            description = "Allow deposits of amounts greater than 499")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A string Deposit successfully made")
    })
    String DepositIntoAccount (String accountNumber, double amount);
    @Operation(
            summary = "Withdraw amount from customer account",
            description = "Allow withdrawal of amount greater than 499")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A string Withdrawal successfully made")
    })
    String withDrawFromAccount(String accountNumber, double amount);

}
