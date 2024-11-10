package com.example.builder;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class BuilderDTO {
    @NotNull(message = "O faturamento não pode ser nulo.")
    private int id;
    @NotNull(message = "O faturamento não pode ser nulo.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O faturamento deve ser maior que zero.")
    private float faturamento;
    private boolean icms;
    private boolean iss;
    @NotNull(message = "O faturamento não pode ser nulo.")
    private int anexo;
    private boolean cofins;
    private boolean csll;
    private boolean pis;


    public BuilderDTO(int id, float faturamento, boolean icms, boolean iss, int anexo, boolean cofins, boolean csll, boolean pis) {
        this.id = id;
        this.faturamento = faturamento;
        this.icms = icms;
        this.iss = iss;
        this.anexo = anexo;
        this.cofins = cofins;
        this.csll = csll;
        this.pis = pis;
    }

    public int getId() {
        return id;
    }

    public float getFaturamento() {
        return faturamento;
    }

    public boolean getIcms() {
        return icms;
    }

    public boolean getIss() {
        return iss;
    }

    public int getAnexo() {
        return anexo;
    }
    
    public boolean getCofins() {
        return cofins;
    }

    public boolean getCsll() {
        return csll;
    }

    public boolean getPis() {
        return pis;
    }
}
