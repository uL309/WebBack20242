package com.example.hub.JWT;

import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {

    

    // Método para gerar o JWT
    public static String generateToken(String username) {
        Key secretKey = JwtConfig.SECRET_KEY;
        long expirationTime = 3600000; // Tempo de expiração do token, aqui definido para 1 hora

        return Jwts.builder()
                .setSubject(username) // Definir o usuário
                .setIssuedAt(new Date()) // Data de criação do token
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Data de expiração
                .signWith(secretKey) // Assinatura com chave secreta
                .compact();
    }
}

