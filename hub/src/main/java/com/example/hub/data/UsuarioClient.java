package com.example.hub.data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Builder", url = "http://:8080") // acertar servidor
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    UsuarioDTO buscarPorId(@PathVariable Long id);
}

