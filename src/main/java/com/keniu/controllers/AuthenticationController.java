package com.keniu.controllers;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;
import com.keniu.dto.UserLoginDto;
import com.keniu.dto.UserLoginRequestDto;
import com.keniu.exceptions.RegistrationException;
import com.keniu.services.AuthenticationService;
import com.keniu.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(
        name = "User Management",
        description = "This section provides endpoints for user management,"
        + " including registration and login. "
        + "Users can register to access the application"
        + " and log in to obtain authentication tokens. "
        + "These endpoints are public and do not require authentication."
)
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "User registration",
            description = "Register a new user in the book shop application.")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody CreateUserRequestDto createUserRequestDto)
            throws RegistrationException {
        return userService.save(createUserRequestDto);
    }

    @PostMapping("/login")
    public UserLoginDto login(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.authenticate(userLoginRequestDto);
    }
}
