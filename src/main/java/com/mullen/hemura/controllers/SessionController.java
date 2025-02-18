package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.services.SessionService;
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
    public List<SessionEntity> getSessions() {
        return sessionService.getAll();
    }

    @GetMapping("code/{code}")
    public SessionEntity getSessionByCode(@PathVariable String code) {
        return sessionService.getByCode(code);
    }

    @PostMapping("userId/{userId}")
    public SessionEntity createSession(@PathVariable String userId, @RequestBody String sessionName) {
        return sessionService.save(userId, sessionName);
    }

    @PutMapping("userId/{userId}/{code}")
    public SessionEntity updateSession(@PathVariable String userId, @PathVariable String code) {
        return sessionService.addUserToSession(userId, code);
    }

    @DeleteMapping("userId/{userId}")
    public void deleteSession(@PathVariable String userId) {
        sessionService.removeUserFromSession(userId);
    }

    @DeleteMapping("code/{code}")
    public void deleteSessionByCode(@PathVariable String code) {
        sessionService.deleteSession(code);
    }
}
