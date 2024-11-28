package com.keniu.services;

import com.keniu.models.Role;
import com.keniu.models.RoleName;

public interface RoleService {

    Role findByName(RoleName roleName);
}
