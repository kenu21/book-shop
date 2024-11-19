package com.keniu.dto;

import com.keniu.validations.FieldMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for
 * representing a user creation request in the books shop application.
 * This class is used to transfer user input data for creating a new user
 * between the client and the server.
 *
 * It includes validation constraints for ensuring valid input values,
 * such as non-blank fields, proper password length, and matching passwords.
 */
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
