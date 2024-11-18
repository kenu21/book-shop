package com.keniu.services;

import com.keniu.exceptions.EntityNotFoundException;
import com.keniu.models.Role;
import com.keniu.models.RoleName;
import com.keniu.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleName roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() ->
            new EntityNotFoundException("Can't find role by name: " + roleName));
    }
}
