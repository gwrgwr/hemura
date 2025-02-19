package com.mullen.hemura.repositories;

import com.mullen.hemura.domain.tasks.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, String> {}
