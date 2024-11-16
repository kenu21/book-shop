package com.keniu.services;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;
import com.keniu.mappers.UserMapper;
import com.keniu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(CreateUserRequestDto createUserRequestDto) {
        createUserRequestDto.setPassword(
                passwordEncoder.encode(createUserRequestDto.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.toModel(createUserRequestDto)));
    }
}
