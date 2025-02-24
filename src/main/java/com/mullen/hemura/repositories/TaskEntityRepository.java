package com.mullen.hemura.repositories;

import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, String> {
    Optional<List<TaskEntity>> findAllBySession_Id(String sessionId);
    Optional<TaskEntity> findByIdAndSession_Id(String taskId, String sessionId);
    Optional<List<TaskEntity>> findAllBySession_IdAndWeekDay(String sessionId, String weekDay);
}
