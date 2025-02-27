package com.mullen.hemura.controllers;

import com.mullen.hemura.domain.user.UserEntity;
import com.mullen.hemura.domain.user.dto.request.UserEntityRequestDTO;
import com.mullen.hemura.domain.user.dto.request.LoginUserEntityDTO;
import com.mullen.hemura.domain.user.dto.request.UpdateUserDTO;
import com.mullen.hemura.domain.user.dto.response.LoggedUserEntityDTO;
import com.mullen.hemura.domain.user.dto.response.UserEntityResponseDTO;
import com.mullen.hemura.services.AuthServices;
import com.mullen.hemura.services.UserEntityServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "Handle User Events")
public class UserEntityController {

    private final UserEntityServices userEntityServices;
    private final AuthServices authServices;

    public UserEntityController(UserEntityServices userEntityServices, AuthServices authServices) {
        this.userEntityServices = userEntityServices;
        this.authServices = authServices;
    }

    @Operation(description = "Get all users from db", summary = "Get all users from db")
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(this.userEntityServices.getAll());
    }

    @Operation(description = "Get one user by email", summary = "Get one user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userEntityServices.getByEmail(email));
    }

    @Operation(description = "Create one user", summary = "Create one user")
    @PostMapping
    public ResponseEntity<UserEntityResponseDTO> save(@RequestBody UserEntityRequestDTO userEntityRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userEntityServices.save(userEntityRequestDTO));
    }

    @Operation(description = "Log in User to application", summary = "Log in User to application")
    @PostMapping("/login")
    public ResponseEntity<LoggedUserEntityDTO> login(@RequestBody LoginUserEntityDTO loginUserEntityDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.authServices.loginUserEntity(loginUserEntityDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(description = "Update a user by id", summary = "Update a user by id")
    @PutMapping("/{id}")
    public ResponseEntity<UserEntityResponseDTO> update(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userEntityServices.update(id, updateUserDTO));
    }

    @Operation(description = "Delete a user by id", summary = "Delete a user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        this.userEntityServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
