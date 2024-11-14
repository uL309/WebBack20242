package com.example.builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "hub", url="http://localhost:8080") // acertar servidor
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    UsuarioDTO buscarPorId(@PathVariable("id") Integer id);
}

