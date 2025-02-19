package com.mullen.hemura.mappers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.session.dto.response.SessionResponseDTO;
import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SessionEntityMapper {
    public  static SessionResponseDTO toResponseDTO(SessionEntity sessionEntity) {
        List<UserEntity> users = sessionEntity.getUsers();
        List<UserEntityResponseDTO> userEntityResponseDTOList = new ArrayList<>();
        for (UserEntity userEntity : users) {
            userEntityResponseDTOList.add(UserEntityMapper.toEntityResponseDTO(userEntity));
        }
        return new SessionResponseDTO(sessionEntity.getId(), sessionEntity.getName(), sessionEntity.getCode(), userEntityResponseDTOList, sessionEntity.getTasks());
    }
}
