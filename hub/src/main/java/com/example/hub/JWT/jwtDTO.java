package com.example.hub.JWT;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class jwtDTO {
    private String token;
    private long id;
    private String role;
}
