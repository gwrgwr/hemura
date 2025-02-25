package com.mullen.hemura.services;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.request.TaskRequestDTO;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;
import com.mullen.hemura.mappers.TaskEntityMapper;
import com.mullen.hemura.repositories.TaskEntityRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public List<TaskResponseDTO> getTasks(String sessionId) {
        return TaskEntityMapper.toTaskListResponseDTO(this.taskEntityRepository.findAllBySession_Id((sessionId)).orElseThrow(() -> new RuntimeException("Tasks not found")));
    }

    public List<TaskResponseDTO> getTaskByWeekDay(String sessionId, String weekDay) {
        return TaskEntityMapper.toTaskListResponseDTO(this.taskEntityRepository.findAllBySession_IdAndWeekDay(sessionId, DayOfWeek.valueOf(weekDay)).orElse(new ArrayList<>()));
    }

    public TaskEntity getTask(String taskId, String sessionId) {
        return this.taskEntityRepository.findByIdAndSession_Id(taskId, sessionId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskResponseDTO update(String taskId, String sessionId, TaskRequestDTO taskRequestDTO) {
        TaskEntity task = this.getTask(taskId, sessionId);
        if (taskRequestDTO.title() != null) {
            task.setTitle(taskRequestDTO.title());
        }
        if (taskRequestDTO.description() != null) {
            task.setDescription(taskRequestDTO.description());
        }
        if (taskRequestDTO.time() != null) {
            var formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time =  LocalTime.parse(taskRequestDTO.time(), formatter);
            task.setTime(time);
        }
        if (taskRequestDTO.weekDay() != null) {
            task.setWeekDay(DayOfWeek.valueOf(taskRequestDTO.weekDay()));
        }
        return TaskEntityMapper.toTaskResponseDTO(this.taskEntityRepository.save(task));
    }

    public TaskResponseDTO updateCompleted(String taskId, String sessionId) {
        TaskEntity task = this.getTask(taskId, sessionId);
        task.setIsCompleted(!task.getIsCompleted());
        return TaskEntityMapper.toTaskResponseDTO(this.taskEntityRepository.save(task));
    }

    public void delete(String taskId, String sessionId) {
        TaskEntity task = this.getTask(taskId, sessionId);
        this.taskEntityRepository.delete(task);
    }
}