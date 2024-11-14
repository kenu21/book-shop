package com.keniu.services;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Saves a new user entity based on the provided details.
     *
     * @param createUserRequestDto a {@link CreateUserRequestDto}
     * containing the details of the user to save
     * @return the saved user entity as a {@link UserDto}
     */
    UserDto save(CreateUserRequestDto createUserRequestDto);
}
