package com.example.imposto;

public class Mei implements Users {
    private String nome;
    private String email;
    private String senha;
    private String role = "MEI";
    private Boolean icms;
    private int valoricms = 1;
    private Boolean iss;
    private int valoriss = 5;
    private double faturamento;

    public Mei(String nome, String email, String senha, double faturamento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.faturamento = faturamento;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getRole() {
        return this.role;
    }

    public double getFaturamento() {
        return this.faturamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public void setServices(Boolean icms, Boolean iss) {
        this.icms = icms;
        this.iss = iss;
    }

    public void getServices() {
        //chamar os serviços via thread
        System.out.println("Serviços disponíveis para " + this.nome + ":");
        System.out.println("INSS: True");
        System.out.println("ICMS: " + this.icms);
        System.out.println("ISS: " + this.iss);
    }

    public Boolean ICMS() {
        return icms;
    }

    public Boolean ISS() {
        return iss;
    }
    
    public double calcularImposto() {
        double imposto = 0;
        //salário mínimo deve vir do banco se possivel ou alguma variavel que sejá facilmente editavel
        double salMinimo = 1100;
        imposto = salMinimo * 0.05;
        if (icms) {
            imposto += valoricms;
        }
        if (iss) {
            imposto += valoriss;
        }
        return imposto;
    }

}
