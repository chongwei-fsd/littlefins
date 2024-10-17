package com.cw.littlefins_proj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name ="email", columnNames = "email")})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, message = "Username must be at least 3 characters.")
    @Size(max = 255, message = "Name must not be more than 255 characters.")
    private String name;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email is not valid.")
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be blank.")
    private EnumRole role;

    @Column
    private Long coin=0L;

    @Column
    private LocalDate dob;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
