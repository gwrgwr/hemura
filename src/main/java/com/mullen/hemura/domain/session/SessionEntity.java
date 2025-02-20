package com.mullen.hemura.domain.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mullen.hemura.domain.tasks.TaskEntity;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id")
    private String id;

    private String name;

    private String code;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "session", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UserEntity> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TaskEntity> tasks;

    public SessionEntity(String name, String code, List<UserEntity> users) {
        this.name = name;
        this.code = code;
        this.users = users;
    }
}
