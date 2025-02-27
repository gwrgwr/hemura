package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.request.TaskRequestDTO;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;
import com.mullen.hemura.mappers.TaskEntityMapper;
import com.mullen.hemura.services.TaskEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@Tag(name = "Tasks", description = "Handle Task Events")
public class TaskEntityController {

    private final TaskEntityService taskEntityService;

    public TaskEntityController(TaskEntityService taskEntityService) {
        this.taskEntityService = taskEntityService;
    }

    @Operation(description = "Get all the tasks from session", summary = "Get all the tasks from session")
    @GetMapping("/sessionId/{sessionId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@PathVariable String sessionId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskEntityService.getTasks(sessionId));
    }

    @Operation(description = "Get one task by id and session", summary = "Get one task by id and session")
    @GetMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable String sessionId, @PathVariable String taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(TaskEntityMapper.toTaskResponseDTO(this.taskEntityService.getTask(taskId, sessionId)));
    }

    @Operation(description = "Get all the tasks from a specific day", summary = "Get all the tasks from a specific day")
    @GetMapping("/sessionId/{sessionId}/weekday/{weekday}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByWeekday(@PathVariable String sessionId, @PathVariable String weekday) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskEntityService.getTaskByWeekDay(sessionId, weekday));
    }

    @Operation(description = "Create one task to session", summary = "Create one task to session")
    @PostMapping("/sessionId/{sessionId}")
    public ResponseEntity<TaskResponseDTO> createTask(@PathVariable String sessionId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskEntityService.save(taskRequestDTO, sessionId));
    }

    @Operation(description = "Update one task from session", summary = "Update one task from session")
    @PutMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable String sessionId, @PathVariable String taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskEntityService.update(taskId, sessionId, taskRequestDTO));
    }

    @Operation(description = "Delete one task from session", summary = "Delete one task from session")
    @DeleteMapping("/sessionId/{sessionId}/taskId/{taskId}")
    public void deleteTask(@PathVariable String sessionId, @PathVariable String taskId) {
        this.taskEntityService.delete(taskId, sessionId);
    }
}
