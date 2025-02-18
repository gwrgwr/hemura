package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.CreateUserEntityDTO;
import com.mullen.hemura.domain.user.dto.request.LoginUserEntityDTO;
import com.mullen.hemura.domain.user.dto.request.UpdateUserDTO;
import com.mullen.hemura.domain.user.dto.response.LoggedUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;
import com.mullen.hemura.services.AuthServices;
import com.mullen.hemura.services.UserEntityServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserEntityController {

    private final UserEntityServices userEntityServices;
    private final AuthServices authServices;

    public UserEntityController(UserEntityServices userEntityServices, AuthServices authServices) {
        this.userEntityServices = userEntityServices;
        this.authServices = authServices;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(this.userEntityServices.getAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userEntityServices.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserEntityResponseDTO> save(@RequestBody CreateUserEntityDTO createUserEntityDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userEntityServices.save(createUserEntityDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedUserEntityDTO> login(@RequestBody LoginUserEntityDTO loginUserEntityDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.authServices.loginUserEntity(loginUserEntityDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityResponseDTO> update(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userEntityServices.update(id, updateUserDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        this.userEntityServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
