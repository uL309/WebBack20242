package com.example.builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "hub", url="http://localhost:8080") // acertar servidor
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    static
    UsuarioDTO buscarPorId(@PathVariable int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }
}

