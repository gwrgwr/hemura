package com.mullen.hemura.services;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.request.TaskRequestDTO;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;
import com.mullen.hemura.mappers.TaskEntityMapper;
import com.mullen.hemura.repositories.TaskEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskEntityService {
    private final TaskEntityRepository taskEntityRepository;
    private final SessionService sessionService;

    public TaskEntityService(TaskEntityRepository taskEntityRepository, SessionService sessionService) {
        this.taskEntityRepository = taskEntityRepository;
        this.sessionService = sessionService;
    }

    public TaskResponseDTO save(TaskRequestDTO taskRequestDTO, String sessionId) {
        SessionEntity session = this.sessionService.getById(sessionId);
        TaskEntity task = TaskEntityMapper.toEntity(taskRequestDTO, session);
        return TaskEntityMapper.toTaskResponseDTO(this.taskEntityRepository.save(task));
    }
}
