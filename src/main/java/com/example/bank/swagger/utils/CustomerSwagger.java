package com.example.bank.swagger.utils;

import com.example.bank.User.Customer.dto.createCustomerDto;
import com.example.bank.User.Customer.dto.newCustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Customer",description = "Customer api")
public interface CustomerSwagger {

    @Operation(
            summary = "Creates a new customer",
            description = "creates a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "an object of some properties of the new account")
    })
     newCustomerDto createCustomer( createCustomerDto customer);
}
