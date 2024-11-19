package com.keniu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for
 * representing the login response for a user in the books shop application.
 * This class is used to transfer the authentication token between the server and the client
 * after a successful login.
 */
@AllArgsConstructor
@Data
public class UserLoginDto {
    private String token;
}
