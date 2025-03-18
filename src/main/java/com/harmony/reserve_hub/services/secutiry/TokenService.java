package com.harmony.reserve_hub.services.secutiry;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.harmony.reserve_hub.domain.User;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secrete.token}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("hostly")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withClaim("name", user.getName())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(this.generateExpirationToken())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException exception){
            throw new CustomException("Error while authenticate", HttpStatus.UNAUTHORIZED);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("hostly")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
