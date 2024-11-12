package com.example.builder;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {
    private Integer id;
    @NotBlank
    private String username;
    private String password;
    private String role;

    String getName() {
        return username;
    }

    String getRole() {
        return role;
    }

    public Integer getId() {
        return id;
    }

}