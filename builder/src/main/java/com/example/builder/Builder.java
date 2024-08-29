package com.example.builder;

public class Builder {
    private String name;
    private String role;
    private Double faturamento;
    private Double imposto=0.0;

    public Builder(String name, String role, Double faturamento) {
        this.name = name;
        this.role = role;
        this.faturamento = faturamento;
    }

    public void addImposto(Double imposto) {
        this.imposto =+ imposto;
    }

    public Double getFaturamento() {
        return faturamento;
    }
    
    public Double getImposto() {
        return imposto;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }

}
