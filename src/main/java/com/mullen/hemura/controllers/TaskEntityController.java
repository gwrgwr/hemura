package com.mullen.hemura.controllers;

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

    @PostMapping("/sessionId/{sessionId}")
    public TaskResponseDTO createTask(@PathVariable String sessionId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return this.taskEntityService.save(taskRequestDTO, sessionId);
    }
}
