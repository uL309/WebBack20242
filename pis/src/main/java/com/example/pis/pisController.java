package com.example.pis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class pisController {

    @Operation(summary = "Get pis taxes", description = "Get pis taxes")
    @Tag(name = "Pis", description = "This API process pis taxes")
    @GetMapping("/simples")
    public ResponseDTO getImposto(@RequestParam(name = "faturamento") double faturamento) {
        double imposto = 0;
        imposto= faturamento * 0.05;
        return new ResponseDTO(imposto);
    }
}