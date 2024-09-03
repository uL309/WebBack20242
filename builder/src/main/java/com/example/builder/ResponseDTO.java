package com.example.builder;

public class ResponseDTO {
    private String name;
    private String role;
    private float faturamento;
    private float imposto;

    public ResponseDTO(String name, String role, float faturamento, float imposto) {
        this.name = name;
        this.role = role;
        this.faturamento = faturamento;
        this.imposto = imposto;
    }

    public ResponseDTO(String name, String role, float faturamento) {
        this.name = name;
        this.role = role;
        this.faturamento = faturamento;
    }

    public ResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public float getFaturamento() {
        return faturamento;
    }

    public float getImposto() {
        return imposto;
    }

    public void setImposto(float imposto) {
        this.imposto = imposto;
    }

    public void setFaturamento(float faturamento) {
        this.faturamento = faturamento;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

}
