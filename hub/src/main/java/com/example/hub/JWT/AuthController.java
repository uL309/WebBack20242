package com.example.hub.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import com.example.hub.data.*;

import java.util.List;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private usuarioService usuarioService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private int jwtExpirationMs = 86400000; // 1 dia

    @PostMapping("/login")
    public String authenticateUser(@RequestBody UsuarioDTO authRequest) {
        List<Usuario> users = usuarioService.listar();
        for (Usuario user : users) {
            if (authRequest.getUsername().equals(user.getUsername()) && authRequest.getPassword().equals(user.getPassword())) {
                //adicionar aqui rabitmq, com a data de expiração do token
                return Jwts.builder()
                        .setSubject(authRequest.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                        .signWith(new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.getJcaName()), SignatureAlgorithm.HS512)
                        .compact();
            }
        }
        return "Invalid username or password";
    }

}
