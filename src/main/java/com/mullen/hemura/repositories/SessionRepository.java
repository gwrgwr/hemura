package com.mullen.hemura.repositories;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {
    Optional<SessionEntity> findByCode(String code);
    Optional<SessionEntity> findFirstByUsersContains(UserEntity userEntity);
}
