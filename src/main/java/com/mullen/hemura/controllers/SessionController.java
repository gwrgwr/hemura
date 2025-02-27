package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.session.dto.response.SessionResponseDTO;
import com.mullen.hemura.services.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/session")
@Tag(name = "Session", description = "Handle Session Events")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(description = "Get all the sessions from db", summary = "Get all the sessions from db")
    @GetMapping
    public List<SessionResponseDTO> getSessions() {
        return sessionService.getAll();
    }

    @Operation(description = "Get all the sessions from user", summary = "Get all the sessions from user")
    @GetMapping("/userId/{userId}")
    public SessionResponseDTO getSessionsByUserId(@PathVariable String userId) {
        return this.sessionService.getSessionByUser(userId);
    }

    @Operation(description = "Get one session by session code", summary = "Get one session by session code")
    @GetMapping("code/{code}")
    public SessionEntity getSessionByCode(@PathVariable String code) {
        return sessionService.getByCode(code);
    }

    @Operation(description = "Create one session and bind it to a user", summary = "Create one session and bind it to a user")
    @PostMapping("userId/{userId}")
    public ResponseEntity<SessionResponseDTO> createSession(@PathVariable String userId, @RequestBody String sessionName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.save(userId, sessionName));
    }

    @Operation(description = "Update one session by user id and session code", summary = "Update one session by user id and session code")
    @PutMapping("userId/{userId}/{code}")
    public SessionResponseDTO updateSession(@PathVariable String userId, @PathVariable String code) {
        return sessionService.addUserToSession(userId, code);
    }

    @Operation(description = "Delete user from session", summary = "Delete user from session")
    @DeleteMapping("userId/{userId}")
    public void deleteSession(@PathVariable String userId) {
        sessionService.removeUserFromSession(userId);
    }

    @Operation(description = "Delete session from db", summary = "Delete session from db")
    @DeleteMapping("code/{sessionId}")
    public void deleteSessionByCode(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId);
    }
}
