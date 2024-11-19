package com.example.hub;

import org.springframework.web.bind.annotation.RestController;

import com.example.hub.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class HubController {
    
    @Autowired
    private usuarioService usuarioService;

    private ModelMapper modelMapper = new ModelMapper();

    Logger logger = LoggerFactory.getLogger(HubController.class);

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = this.modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("Atualizando usuário");
        Usuario usuario = this.modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.atualizar(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    @DeleteMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public void excluir(Integer id) {
        usuarioService.excluir(id);
    }
    
    @GetMapping("/usuarios/{id}")
    public UsuarioDTO buscarPorId(@PathVariable("id") int param) {
        return this.modelMapper.map(usuarioService.buscarPorId(param), UsuarioDTO.class);
    }
    

}
