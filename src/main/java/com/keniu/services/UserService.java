package com.keniu.services;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;

public interface UserService {
    
    UserDto save(CreateUserRequestDto createUserRequestDto);
}
