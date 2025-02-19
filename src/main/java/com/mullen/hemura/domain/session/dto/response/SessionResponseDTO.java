package com.mullen.hemura.domain.session.dto.response;

import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;

import java.util.List;

public record SessionResponseDTO(String id, String name, String code, List<UserEntityResponseDTO> users, List<TaskEntity> tasks) {}
