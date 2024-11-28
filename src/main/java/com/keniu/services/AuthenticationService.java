package com.keniu.services;

import com.keniu.dto.UserLoginDto;
import com.keniu.dto.UserLoginRequestDto;

public interface AuthenticationService {

    UserLoginDto authenticate(UserLoginRequestDto userLoginRequestDto);
}
