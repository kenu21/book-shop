package com.keniu.dto;

import com.keniu.validations.FieldMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
@Data
public class CreateUserRequestDto {

    @NotBlank
    @Size(min = 6, max = 255)
    private String email;
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;
    @NotBlank
    @Size(min = 8, max = 255)
    private String repeatPassword;
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @Size(max = 255)
    private String shippingAddress;
}
