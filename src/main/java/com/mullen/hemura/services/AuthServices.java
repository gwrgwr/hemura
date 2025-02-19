package com.mullen.hemura.services;

import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.LoginUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.LoggedUserEntityDTO;
import com.mullen.hemura.mappers.UserEntityMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final UserEntityServices userEntityServices;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    public AuthServices(UserEntityServices userEntityServices, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService) {
        this.userEntityServices = userEntityServices;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    public LoggedUserEntityDTO loginUserEntity(LoginUserEntityDTO loginUserEntityDTO) throws Exception{
        UserEntity userEntity = this.userEntityServices.getByEmail(loginUserEntityDTO.email());
        if (!bCryptPasswordEncoder.matches(loginUserEntityDTO.password(), userEntity.getPassword())) {
            throw new Exception("Invalid Credentials");
        }
        String token = tokenService.generateToken(userEntity);
        return UserEntityMapper.toLoggedUserEntityDTO(token, userEntity, userEntity.getSession());
    }
}
