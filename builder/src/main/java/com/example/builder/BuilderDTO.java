package com.example.builder;

public class BuilderDTO {
    private int id;
    private String name;
    private String role;
    private float faturamento;
    private boolean icms;
    private boolean iss;
    private int anexo;
    private boolean cofins;
    private boolean csll;
    private boolean pis;


    public BuilderDTO(int id,String name, String role, float faturamento, boolean icms, boolean iss, int anexo, boolean cofins, boolean csll, boolean pis) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
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
