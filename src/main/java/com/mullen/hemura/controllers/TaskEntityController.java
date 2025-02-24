package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.request.TaskRequestDTO;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;
import com.mullen.hemura.services.TaskEntityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
public class TaskEntityController {

    private final TaskEntityService taskEntityService;

    public TaskEntityController(TaskEntityService taskEntityService) {
        this.taskEntityService = taskEntityService;
    }

    @GetMapping("/sessionId/{sessionId}")
    public List<TaskResponseDTO> getTasks(@PathVariable String sessionId) {
        return this.taskEntityService.getTasks(sessionId);
    }

    @GetMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public TaskEntity getTask(@PathVariable String sessionId, @PathVariable String taskId) {
        return this.taskEntityService.getTask(taskId, sessionId);
    }

    @GetMapping("/sessionId/{sessionId}/weekday/{weekday}")
    public List<TaskResponseDTO> getTasksByWeekday(@PathVariable String sessionId, @PathVariable String weekday) {
        return this.taskEntityService.getTaskByWeekDay(sessionId, weekday);
    }

    @PostMapping("/sessionId/{sessionId}")
    public TaskResponseDTO createTask(@PathVariable String sessionId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return this.taskEntityService.save(taskRequestDTO, sessionId);
    }

    @PutMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable String sessionId, @PathVariable String taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return this.taskEntityService.update(taskId, sessionId, taskRequestDTO);
    }

    @DeleteMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public void deleteTask(@PathVariable String sessionId, @PathVariable String taskId) {
        this.taskEntityService.delete(taskId, sessionId);
    }
}
