package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a role in the books shop application. A role is assigned to users to define their
 * level of access and permissions. This entity supports soft deletion, where a role can be marked
 * as deleted without being removed from the database.
 */
@Entity
@SQLDelete(sql = "UPDATE roles SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role, which should be unique.
     */
    @NotNull
    @Column(nullable = false, unique = true)
    @Size(max = 255)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    /**
     * Indicates if the role is marked as deleted (soft delete). A role marked as deleted won't be
     * included in application logic, but still exists in the database.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
