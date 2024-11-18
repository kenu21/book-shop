package com.keniu.services;

import com.keniu.models.Role;
import com.keniu.models.RoleName;

/**
 * Service interface for managing role-related operations.
 */
public interface RoleService {

    /**
     * Finds a role entity by its name.
     *
     * @param roleName a {@link RoleName} representing the name of the role to find
     * @return the found role entity as a {@link Role}
     * @throws IllegalStateException if the role with the specified name is not found
     */
    Role findByName(RoleName roleName);
}
