package com.mullen.hemura.domain.user.dto.response;

public record LoggedUserEntityDTO(String token, String id, String name, String lastName, String email) {
}
