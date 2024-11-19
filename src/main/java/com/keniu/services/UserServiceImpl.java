package com.keniu.services;

import com.keniu.dto.CreateUserRequestDto;
import com.keniu.dto.UserDto;
import com.keniu.mappers.UserMapper;
import com.keniu.models.RoleName;
import com.keniu.models.User;
import com.keniu.repositories.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.toModel(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.findByName(RoleName.ROLE_USER)));
        return userMapper.toDto(userRepository.save(user));
    }
}
