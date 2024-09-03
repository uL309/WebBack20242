package com.example.mei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class MeiController {

    @Operation(summary = "Get MEI taxes", description = "Get MEI taxes")
    @Tag(name = "MEI", description = "This API process MEI taxes")
    @GetMapping("/mei")
    public ResponseDTO getImposto(@RequestParam(name = "icms") boolean icms, @RequestParam(name = "iss") boolean iss) {
        float imposto = 0;
        //salário mínimo deve vir do banco se possivel ou alguma variavel que sejá facilmente editavel
        float salMinimo = 1100;
        imposto = (float) (salMinimo * 0.05);
        if (icms) {
            imposto += 1;
        }
        if (iss) {
            imposto += 5;
        }
        return new ResponseDTO(imposto);
    }
}