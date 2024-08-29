package com.example.mei;

public class ResponseDTO {
    private double imposto;

    public ResponseDTO(double imposto) {
        this.imposto = imposto;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }
}
