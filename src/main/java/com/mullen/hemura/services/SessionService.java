package com.mullen.hemura.services;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.session.dto.response.SessionResponseDTO;
import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;
import com.mullen.hemura.mappers.SessionEntityMapper;
import com.mullen.hemura.mappers.UserEntityMapper;
import com.mullen.hemura.repositories.SessionRepository;
import com.mullen.hemura.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserEntityRepository userEntityRepository;

    public SessionService(SessionRepository sessionRepository, UserEntityRepository userEntityRepository) {
        this.sessionRepository = sessionRepository;
        this.userEntityRepository = userEntityRepository;
    }


    private final SecureRandom random = new SecureRandom();

    public String generateCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    public List<SessionResponseDTO> getAll() {
        List<SessionResponseDTO> sessionResponseDTOList = new ArrayList<>();
        for (SessionEntity sessionEntity : sessionRepository.findAll()) {
            sessionResponseDTOList.add(SessionEntityMapper.toResponseDTO(sessionEntity));
        }
        return sessionResponseDTOList;
    }

    public SessionResponseDTO getSessionByUser(String userId) {
        UserEntity userEntity = this.userEntityRepository.getReferenceById(userId);
        SessionEntity session = this.sessionRepository.findFirstByUsersContains(userEntity).orElseThrow(() -> new RuntimeException("Session not found"));
        List<UserEntityResponseDTO> users = new ArrayList<>();
        return SessionEntityMapper.toResponseDTO(session);
    }

    public SessionResponseDTO save(String userId, String sessionName) {
        UserEntity user = this.userEntityRepository.getReferenceById(userId);
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        String code = generateCode(6);
        SessionEntity session = new SessionEntity(sessionName, code, users);
        user.setSession(session);
        this.sessionRepository.save(session);
        this.userEntityRepository.saveAndFlush(user);
        return SessionEntityMapper.toResponseDTO(session);
    }

    public SessionEntity getByCode(String code) {
        return sessionRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public SessionResponseDTO addUserToSession(String userId, String code) {
        UserEntity user = this.userEntityRepository.getReferenceById(userId);
        SessionEntity session = getByCode(code);
        session.getUsers().add(user);
        user.setSession(session);
        this.sessionRepository.save(session);
        this.userEntityRepository.saveAndFlush(user);
        return SessionEntityMapper.toResponseDTO(session);
    }

    public void removeUserFromSession(String userId) {
        UserEntity user = this.userEntityRepository.getReferenceById(userId);
        SessionEntity session = user.getSession();
        session.getUsers().remove(user);
        user.setSession(null);
        this.sessionRepository.save(session);
        this.userEntityRepository.saveAndFlush(user);

    }

    public void deleteSession(String sessionId) {
        SessionEntity session = this.sessionRepository.getReferenceById(sessionId);
        if (session != null) {
            for (UserEntity user : session.getUsers()) {
                user.setSession(null);
                this.userEntityRepository.saveAndFlush(user);
            }
            this.sessionRepository.delete(session);
        } else {
            throw new RuntimeException("Session not found");
        }
    }
}
