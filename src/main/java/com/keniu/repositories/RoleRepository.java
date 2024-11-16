package com.keniu.repositories;

import com.keniu.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Role} entities.
 * Provides methods to interact with the role data in the database.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
