package com.keniu.services;

import com.keniu.dto.UserLoginDto;
import com.keniu.dto.UserLoginRequestDto;
import com.keniu.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserLoginDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userLoginRequestDto.getEmail(),
                userLoginRequestDto.getPassword()
            )
        );
        String token = jwtUtil.generateToken(userLoginRequestDto.getEmail());
        return new UserLoginDto(token);
    }
}
