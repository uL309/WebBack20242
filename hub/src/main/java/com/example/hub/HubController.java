package com.example.hub;

import org.springframework.web.bind.annotation.RestController;

import com.example.hub.data.*;
import org.modelmapper.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class HubController {
    
    @Autowired
    private usuarioService usuarioService;

    private ModelMapper modelMapper = new ModelMapper();;

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = this.modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = this.modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    @DeleteMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public void excluir(Integer id) {
    }
    


}
