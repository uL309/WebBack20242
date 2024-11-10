package com.example.mei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class cofinsController {

    @Operation(summary = "Get ccofins taxes", description = "Get cofins taxes")
    @Tag(name = "Cofins", description = "This API process cofins taxes")
    @GetMapping("/simples")
    public ResponseDTO getImposto(@RequestParam(name = "faturamento") double faturamento) {
        double imposto = 0;
        imposto= faturamento * 0.15;
        return new ResponseDTO(imposto);
    }
}