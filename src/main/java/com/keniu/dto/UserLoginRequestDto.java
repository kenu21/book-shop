package com.keniu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for user login request
 * in the book shop application.
 * Contains the necessary information for authenticating a user
 * during the login process, such as email and password.
 */

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 6, max = 255)
    private String email;
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;
}
