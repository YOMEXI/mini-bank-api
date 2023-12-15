package com.example.bank.User.Customer.dto;

import com.example.bank.User.Customer.enums.StaffPosition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class newStaffDto {
    @NotBlank(message = "first name is mandatory")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotNull(message = "Staff position is mandatory")
    private StaffPosition position;

    @NotBlank(message = "email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "insert a valid email")
    private String email;

    private boolean active;
}
