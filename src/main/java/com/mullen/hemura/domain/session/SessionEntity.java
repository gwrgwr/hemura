package com.mullen.hemura.domain.session;

import com.mullen.hemura.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private List<UserEntity> users;

    public SessionEntity(String name, String code, List<UserEntity> users) {
        this.name = name;
        this.code = code;
        this.users = users;
    }
}
