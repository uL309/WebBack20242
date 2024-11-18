package com.example.hub.JWT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hub.data.Usuario;
import com.example.hub.data.UsuarioDTO;
import com.example.hub.data.usuarioService;
import com.example.hub.rabbit.QueueSender;
@RestController
public class AuthController {

    @Autowired
    private usuarioService usuarioService;

    @Autowired
    private QueueSender queue;

    @PostMapping("/login")
    public ResponseEntity<jwtDTO> authenticateUser(@RequestBody UsuarioDTO authRequest) {
        List<Usuario> users = usuarioService.listar();
        for (Usuario user : users) {
            if (authRequest.getUsername().equals(user.getUsername()) && authRequest.getPassword().equals(user.getPassword())) {

                // Gera o token JWT
                String token = JwtTokenUtil.generateToken(authRequest.getUsername());
                jwtDTO jwtDTO = new jwtDTO();
                jwtDTO.setToken(token);
                jwtDTO.setId(user.getId());
                jwtDTO.setRole(user.getRole());
                System.out.println(jwtDTO.getToken());
                this.queue.send("teste", jwtDTO.getToken());
            
                return ResponseEntity.ok(jwtDTO);

            }
        }
        return ResponseEntity.status(401).build();
    }
}
