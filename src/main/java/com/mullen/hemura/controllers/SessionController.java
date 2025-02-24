package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.session.dto.response.SessionResponseDTO;
import com.mullen.hemura.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionResponseDTO> getSessions() {
        return sessionService.getAll();
    }

    @GetMapping("/userId/{userId}")
    public SessionResponseDTO getSessionsByUserId(@PathVariable String userId) {
        return this.sessionService.getSessionByUser(userId);
    }

    @GetMapping("code/{code}")
    public SessionEntity getSessionByCode(@PathVariable String code) {
        return sessionService.getByCode(code);
    }

    @PostMapping("userId/{userId}")
    public ResponseEntity<SessionResponseDTO> createSession(@PathVariable String userId, @RequestBody String sessionName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.save(userId, sessionName));
    }

    @PutMapping("userId/{userId}/{code}")
    public SessionResponseDTO updateSession(@PathVariable String userId, @PathVariable String code) {
        return sessionService.addUserToSession(userId, code);
    }

    @DeleteMapping("userId/{userId}")
    public void deleteSession(@PathVariable String userId) {
        sessionService.removeUserFromSession(userId);
    }

    @DeleteMapping("code/{sessionId}")
    public void deleteSessionByCode(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId);
    }
}
