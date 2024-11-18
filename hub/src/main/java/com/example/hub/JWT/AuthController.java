package com.example.hub.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import com.example.hub.HubController;
import com.example.hub.data.*;

import java.util.List;
import java.security.Key;
import java.util.Date;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(HubController.class);

    @Autowired
    private usuarioService usuarioService;

    @Autowired
    private JwtTokenUtil JwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<jwtDTO> authenticateUser(@RequestBody UsuarioDTO authRequest) {
        List<Usuario> users = usuarioService.listar();
        for (Usuario user : users) {
            if (authRequest.getUsername().equals(user.getUsername()) && authRequest.getPassword().equals(user.getPassword())) {
                logger.info("Usuário" + user.getUsername());
                // Gera o token JWT
                String token = JwtTokenUtil.generateToken(authRequest.getUsername());
                jwtDTO jwtDTO = new jwtDTO();
                jwtDTO.setToken(token);
                jwtDTO.setId(user.getId());
                jwtDTO.setRole(user.getRole());
                
                return ResponseEntity.ok(jwtDTO);

            }
        }
        return ResponseEntity.status(401).build();
    }
}
