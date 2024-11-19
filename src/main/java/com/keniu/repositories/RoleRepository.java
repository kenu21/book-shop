package com.keniu.repositories;

import com.keniu.models.Role;
import com.keniu.models.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Role} entities.
 * Provides methods to interact with the role data in the database.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
