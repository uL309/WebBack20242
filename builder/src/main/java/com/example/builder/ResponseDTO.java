package com.example.builder;

public class ResponseDTO {
    private String name;
    private String role;
    private Double faturamento;
    private Double imposto;

    public ResponseDTO(String name, String role, Double faturamento, Double imposto) {
        this.name = name;
        this.role = role;
        this.faturamento = faturamento;
        this.imposto = imposto;
    }

    public ResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Double getFaturamento() {
        return faturamento;
    }

    public Double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setFaturamento(Double faturamento) {
        this.faturamento = faturamento;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

}
