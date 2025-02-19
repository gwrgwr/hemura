package com.mullen.hemura.domain.user.dto.request;

import com.mullen.hemura.domain.user.Role;

public record UserEntityRequestDTO(String name, String lastName, String email, String password) {
}
