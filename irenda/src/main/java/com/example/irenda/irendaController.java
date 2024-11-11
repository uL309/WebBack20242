package com.example.irenda;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class irendaController {

    @Operation(summary = "Get ir taxes", description = "Get ir taxes")
    @Tag(name = "Irenda", description = "This API process IRPJ taxes")
    @GetMapping("/renda")
    public ResponseDTO getImposto(@RequestParam(name = "faturamento") double faturamento, @RequestParam(name = "anexo") int anexo) {
        double imposto = 0;
        if (anexo==1){
            if (faturamento <= 180000) {
                imposto = faturamento * 0.04;
            } else if (faturamento <= 360000) {
                imposto = faturamento * 0.073;
                imposto = imposto - 5940.0;
            } else if (faturamento <= 720000) {
                imposto = faturamento * 0.095;
                imposto = imposto - 13860.0;
            } else if (faturamento <= 1800000) {
                imposto = faturamento * 0.107;
                imposto = imposto - 22500.0;
            } else if (faturamento <= 3600000) {
                imposto = faturamento * 0.143;
                imposto = imposto - 87300.0;
            } else {
                imposto = faturamento * 0.19;
                imposto = imposto - 378000.0;
            }
        } else if (anexo==2){
            if (faturamento <= 180000) {
                imposto = faturamento * 0.045;
            } else if (faturamento <= 360000) {
                imposto = faturamento * 0.078;
                imposto = imposto - 5940.0;
            } else if (faturamento <= 720000) {
                imposto = faturamento * 0.1;
                imposto = imposto - 13860.0;
            } else if (faturamento <= 1800000) {
                imposto = faturamento * 0.112;
                imposto = imposto - 22500.0;
            } else if (faturamento <= 3600000) {
                imposto = faturamento * 0.147;
                imposto = imposto - 85300.0;
            } else {
                imposto = faturamento * 0.30;
                imposto = imposto - 720000.0;
            }
        } else if (anexo==3){
            if (faturamento <= 180000) {
                imposto = faturamento * 0.06;
            } else if (faturamento <= 360000) {
                imposto = faturamento * 0.112;
                imposto = imposto - 9360.0;
            } else if (faturamento <= 720000) {
                imposto = faturamento * 0.135;
                imposto = imposto - 17640.0;
            } else if (faturamento <= 1800000) {
                imposto = faturamento * 0.16;
                imposto = imposto - 35640.0;
            } else if (faturamento <= 3600000) {
                imposto = faturamento * 0.21;
                imposto = imposto - 125640.0;
            } else {
                imposto = faturamento * 0.33;
                imposto = imposto - 648000.0;
            }
        } else if (anexo==4){
            if (faturamento <= 180000) {
                imposto = faturamento * 0.045;
            } else if (faturamento <= 360000) {
                imposto = faturamento * 0.09;
                imposto = imposto - 8100.0;
            } else if (faturamento <= 720000) {
                imposto = faturamento * 0.102;
                imposto = imposto - 12420.0;
            } else if (faturamento <= 1800000) {
                imposto = faturamento * 0.14;
                imposto = imposto - 39780.0;
            } else if (faturamento <= 3600000) {
                imposto = faturamento * 0.22;
                imposto = imposto - 183780.0;
            } else {
                imposto = faturamento * 0.33;
                imposto = imposto - 828000.0;
            }
        } else if (anexo==5){
            if (faturamento <= 180000) {
                imposto = faturamento * 0.155;
            } else if (faturamento <= 360000) {
                imposto = faturamento * 0.18;
                imposto = imposto - 4500.0;
            } else if (faturamento <= 720000) {
                imposto = faturamento * 0.195;
                imposto = imposto - 9900.0;
            } else if (faturamento <= 1800000) {
                imposto = faturamento * 0.205;
                imposto = imposto - 17100.0;
            } else if (faturamento <= 3600000) {
                imposto = faturamento * 0.23;
                imposto = imposto - 62100.0;
            } else {
                imposto = faturamento * 0.305;
                imposto = imposto - 540000.0;
            }
        } else {
            return new ResponseDTO();
        }
        return new ResponseDTO(imposto);
    }
}