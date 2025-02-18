package com.mullen.hemura.mappers;

import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.CreateUserEntityDTO;
import com.mullen.hemura.domain.user.dto.request.UpdateUserDTO;
import com.mullen.hemura.domain.user.dto.response.LoggedUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;

public class UserEntityMapper {
    public static UserEntity toUserEntity(CreateUserEntityDTO createUserEntityDTO, String encodedPassword) {
        return new UserEntity(createUserEntityDTO.name(), createUserEntityDTO.lastName(), createUserEntityDTO.email(), encodedPassword);
    }

    public static UserEntity toUserEntity(UpdateUserDTO updateUserDTO) {
        return new UserEntity(updateUserDTO.name(), updateUserDTO.lastName(), updateUserDTO.email());
    }

    public static UserEntityResponseDTO toEntityResponseDTO(UserEntity userEntity) {
        return new UserEntityResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getFinalName(), userEntity.getEmail());
    }

    public static LoggedUserEntityDTO toLoggedUserEntityDTO(String token, UserEntity userEntity) {
        return new LoggedUserEntityDTO(token, userEntity.getId(), userEntity.getName(), userEntity.getFinalName(), userEntity.getEmail());
    }
}
