package com.llp.mam.security.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.llp.mam.entity.User;
import com.llp.mam.exception.ErrorGeneratingTokenException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String createToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256("12345");
            return JWT.create()
                    .withIssuer("API MAM")
                    .withSubject(user.getUserName())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new ErrorGeneratingTokenException(exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
