package com.scanai.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.scanai.api.domain.funcionario.Funcionario;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Funcionario funcionario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("scanai-api")
                    .withSubject(funcionario.getUsername())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("scanai-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Access Denied, invalid token", exception);
        }
    }

}
