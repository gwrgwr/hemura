package com.mullen.hemura.services;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserEntityServices userService;

    private final SecureRandom random = new SecureRandom();

    public String generateCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    public List<SessionEntity> getAll() {
        return this.sessionRepository.findAll();
    }

    public SessionService(SessionRepository sessionRepository, UserEntityServices userService) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
    }

    public SessionEntity save(String userId, String sessionName) {
        UserEntity user = userService.getById(userId);
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        String code = generateCode(6);
        SessionEntity session = new SessionEntity(sessionName, code, users);
        return sessionRepository.save(session);
    }

    public SessionEntity getByCode(String code) {
        return sessionRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public SessionEntity addUserToSession(String userId, String code) {
        UserEntity user = userService.getById(userId);
        SessionEntity session = getByCode(code);
        session.getUsers().add(user);
        return sessionRepository.save(session);
    }

    public SessionEntity removeUserFromSession(String userId) {
        UserEntity user = userService.getById(userId);
        SessionEntity session = user.getSession();
        session.getUsers().remove(user);
        return sessionRepository.save(session);
    }

    public void deleteSession(String code) {
        sessionRepository.delete(getByCode(code));
    }
}
