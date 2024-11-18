package com.keniu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 6, max = 255)
    private String email;
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;
}
