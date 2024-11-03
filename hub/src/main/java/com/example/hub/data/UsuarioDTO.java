package com.example.hub.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    @NotBlank
    private String username;
    private String password;
    private String role;
}