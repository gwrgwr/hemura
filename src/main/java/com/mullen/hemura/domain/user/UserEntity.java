package com.mullen.hemura.domain.user;

import com.mullen.hemura.domain.session.SessionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String finalName;

    @Column(unique = true)
    private String email;

    private String password;

    private String googleId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = null;

    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="session_id")
    private SessionEntity session;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UserEntity(String name, String finalName, String email, String password, Role role) {
        this.name = name;
        this.finalName = finalName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String name, String finalName, String email) {
        this.name = name;
        this.finalName = finalName;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.equals(role.getRole(), "admin")) {
            return List.of(() -> "SCOPE_ADMIN", () -> "SCOPE_USER");
        } else {
            return List.of(() -> "SCOPE_USER");
        }
    }

    @Override
    public String getUsername() {
        return "";
    }
}