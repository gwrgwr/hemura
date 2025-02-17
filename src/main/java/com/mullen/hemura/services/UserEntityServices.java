package com.mullen.hemura.services;

import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.CreateUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;
import com.mullen.hemura.mappers.UserEntityMapper;
import com.mullen.hemura.repositories.UserEntityRepository;
import com.mullen.hemura.utils.ReflectionUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityServices {
    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntityServices(UserEntityRepository userEntityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserEntity> getAll() {
        return this.userEntityRepository.findAll();
    }

    public UserEntity getByEmail(String email) {
        return this.userEntityRepository.getByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity getById(String id) {
        return this.userEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntityResponseDTO save(CreateUserEntityDTO createUserEntityDTO) {
        String encodedPassword = bCryptPasswordEncoder.encode(createUserEntityDTO.password());
        return UserEntityMapper.toEntityResponseDTO(this.userEntityRepository.save(UserEntityMapper.toUserEntity(createUserEntityDTO, encodedPassword)));
    }

    public UserEntityResponseDTO update(String id, CreateUserEntityDTO createUserEntityDTO) {
        UserEntity userEntity = this.getById(id);
        ReflectionUpdate.updateEntitiesFields(userEntity, createUserEntityDTO);
        return UserEntityMapper.toEntityResponseDTO(this.userEntityRepository.save(userEntity));
    }

    public void deleteById(String id) {
        this.userEntityRepository.delete(this.userEntityRepository.getReferenceById(id));
    }
}
