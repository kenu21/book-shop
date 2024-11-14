package com.keniu.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for
 * representing a user in the books shop application.
 * This class is used to transfer user data between the client and the server.
 */
@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String shippingAddress;
}
