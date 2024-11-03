package com.example.hub.data;

import org.springframework.data.annotation.Id;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "user")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password") 
    private String password;

    @Column(name = "role")
    private String role;
}
