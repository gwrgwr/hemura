package com.mullen.hemura.services;

import com.mullen.hemura.domain.user.UserEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(UserEntity user) {
        var now = Instant.now();
        var expirationTime = now.plusSeconds(60 * 60 * 24 * 7);

        var claims = JwtClaimsSet.builder()
                .subject(user.getId())
                .claim("scope", user.getRole().name())
                .issuer("hemura")
                .expiresAt(expirationTime)
                .issuedAt(now)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
