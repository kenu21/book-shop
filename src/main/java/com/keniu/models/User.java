package com.keniu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 255)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 8, max = 255)
    private String password;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String shippingAddress;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
