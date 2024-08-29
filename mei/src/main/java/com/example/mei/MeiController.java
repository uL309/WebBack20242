package com.example.mei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeiController {

    @GetMapping("/mei")
    public ResponseDTO getImposto(@RequestParam(name = "icms") boolean icms, @RequestParam(name = "iss") boolean iss) {
        double imposto = 0;
        //salário mínimo deve vir do banco se possivel ou alguma variavel que sejá facilmente editavel
        double salMinimo = 1100;
        imposto = salMinimo * 0.05;
        if (icms) {
            imposto += 1;
        }
        if (iss) {
            imposto += 5;
        }
        return new ResponseDTO(imposto);
    }
}