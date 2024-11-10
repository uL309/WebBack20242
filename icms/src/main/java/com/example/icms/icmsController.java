package com.example.mei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class icmsController {

    @Operation(summary = "Get icms taxes", description = "Get icms taxes")
    @Tag(name = "icms", description = "This API process icms taxes")
    @GetMapping("/simples")
    public ResponseDTO getImposto(@RequestParam(name = "faturamento") double faturamento) {
        double imposto = 0;
        imposto= faturamento * 0.10;
        return new ResponseDTO(imposto);
    }
}