package com.mullen.hemura.domain.user.dto.response;

import com.mullen.hemura.domain.session.SessionEntity;

public record LoggedUserEntityDTO(String token, String id, String name, String lastName, String email, SessionEntity sessionEntity) {
}
