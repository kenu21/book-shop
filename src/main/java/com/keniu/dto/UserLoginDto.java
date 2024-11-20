package com.keniu.dto;

/**
 * Data Transfer Object (DTO) for
 * representing the login response for a user in the books shop application.
 * This record is used to transfer the authentication token between the server and the client
 * after a successful login.
 */
public record UserLoginDto(String token) {
}
