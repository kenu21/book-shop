package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents a user in the books shop application.
 */
@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id=?")
@Filter(name = "softDeleteFilter", condition = "is_deleted = false")
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    /** The unique identifier for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The email address of the user, which should be unique. */
    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 255)
    private String email;

    /** The password of the user. */
    @NotBlank
    @Column(nullable = false)
    @Size(min = 8, max = 255)
    private String password;

    /** The first name of the user. */
    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String firstName;

    /** The last name of the user. */
    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String lastName;

    /** The shipping address of the user. */
    @Size(max = 255)
    private String shippingAddress;

    /** Indicates if the user is marked as deleted (soft delete). */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
