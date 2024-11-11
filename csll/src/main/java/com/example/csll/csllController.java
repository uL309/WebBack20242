package com.example.csll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class csllController {

    @Operation(summary = "Get csll taxes", description = "Get csll taxes")
    @Tag(name = "Csll", description = "This API process csll taxes")
    @GetMapping("/simples")
    public ResponseDTO getImposto(@RequestParam(name = "faturamento") double faturamento) {
        double imposto = 0;
        imposto= faturamento * 0.15;
        return new ResponseDTO(imposto);
    }
}