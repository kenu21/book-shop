package com.keniu.services;

import com.keniu.dto.UserLoginDto;
import com.keniu.dto.UserLoginRequestDto;

/**
 * Service interface for handling authentication-related operations.
 */
public interface AuthenticationService {

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param userLoginRequestDto a {@link UserLoginRequestDto}
     * containing the login details (email and password) of the user to authenticate
     * @return a {@link UserLoginDto} containing the authentication token
     */
    UserLoginDto authenticate(UserLoginRequestDto userLoginRequestDto);
}
