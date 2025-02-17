package com.mullen.hemura.repositories;

import com.mullen.hemura.domain.session.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {
    Optional<SessionEntity> findByCode(String code);
}
