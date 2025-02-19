package com.mullen.hemura.mappers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.user.Role;
import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.UpdateUserDTO;
import com.mullen.hemura.domain.user.dto.request.UserEntityRequestDTO;
import com.mullen.hemura.domain.user.dto.response.LoggedUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;

public class UserEntityMapper {
    public static UserEntity toUserEntity(UserEntityRequestDTO userEntityRequestDTO, String encodedPassword) {
        return new UserEntity(userEntityRequestDTO.name(), userEntityRequestDTO.lastName(), userEntityRequestDTO.email(), encodedPassword, Role.USER);
    }

    public static UserEntity updateToEntity(UpdateUserDTO updateUserDTO) {
        return new UserEntity(updateUserDTO.name(), updateUserDTO.lastName(), updateUserDTO.email());
    }

    public static UserEntityResponseDTO toEntityResponseDTO(UserEntity userEntity) {
        return new UserEntityResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getFinalName(), userEntity.getEmail());
    }

    public static LoggedUserEntityDTO toLoggedUserEntityDTO(String token, UserEntity userEntity, SessionEntity sessionEntity) {
        return new LoggedUserEntityDTO(token, userEntity.getId(), userEntity.getName(), userEntity.getFinalName(), userEntity.getEmail(),  sessionEntity);
    }
}
