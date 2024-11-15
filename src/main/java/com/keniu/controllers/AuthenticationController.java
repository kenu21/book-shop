package com.keniu.controllers;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;
import com.keniu.exceptions.RegistrationException;
import com.keniu.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing authentication operations in the books shop application.
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "User management", description = "Endpoints for managing users")
public class AuthenticationController {
    private final UserService userService;

    /**
     * Registers a new user in the system with the provided details.
     *
     * @param createUserRequestDto a {@link CreateUserRequestDto} containing the user details
     * @return a {@link UserDto} representing the newly created user
     * @throws RegistrationException if there is any error during the registration process
     */
    @Operation(summary = "User registration",
            description = "Register a new user in the book shop application.")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody CreateUserRequestDto createUserRequestDto)
            throws RegistrationException {
        return userService.save(createUserRequestDto);
    }
}
