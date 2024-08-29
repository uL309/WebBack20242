package com.example.imposto;

import java.util.ArrayList;

public class Simples implements Users{
    private String nome;
    private String email;
    private String senha;
    private String role = "Simples";
    private String estado;
    private Boolean icms;
    private int anexo;
    private double faturamento;


    public Simples(String nome, String email, String senha, double faturamento) {
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

    public void setServices(int anexo,Boolean icms) {
        this.anexo = anexo;
        this.icms = icms;
    }

    public void getServices() {
        //chamar os serviços via thread
        System.out.println("Serviços disponíveis para " + this.nome + ":");
        System.out.println("IRPJ: True");
        System.out.println("CSLL: True");
        System.out.println("COFINS: True");
        System.out.println("PIS: True");
        System.out.println("ICMS: " + this.icms);
        System.out.println("Anexo: " + this.anexo);
    }

    public Boolean ICMS() {
        return this.icms;
    }


    public int Anexo() {
        return this.anexo;
    }

    public double calcularIR() {
        double imposto = 0;
        //Valores definidos devem vir do banco de dados
        if (this.faturamento <= 180000d) {
            imposto = faturamento * 0.04d;
        } else if (this.faturamento <= 360000d) {
            imposto = faturamento * 0.06d;
        } else if (this.faturamento <= 720000d) {
            imposto = faturamento * 0.11d;
        } else if (this.faturamento <= 1800000d) {
            imposto = faturamento * 0.22d;
        } else if (this.faturamento <= 3600000d) {
            imposto = faturamento * 0.30d;
        }else{
            return 0;
        }
        return imposto;
    }

    public double calcularCSLL() {
        double imposto = 0;
        //Valores definidos devem vir do banco de dados
        imposto = faturamento * 0.05d;
        return imposto;
    }

    public double calcularCOFINS() {
        double imposto = 0;
        //Valores definidos devem vir do banco de dados
        imposto = faturamento * 0.076d;
        return imposto;
    }

    public double calcularPIS() {
        double imposto = 0;
        //Valores definidos devem vir do banco de dados
        imposto = faturamento * 0.0165d;
        return imposto;
    }

    public double calcularICMS() {
        double imposto = 0;
        double aliquota = 0;
        //aqui é preciso do banco de dados para pegar a aliquota do estado
        imposto = faturamento * aliquota;
        return imposto;
    }

    public double calcularAnexo() {
        double imposto = 0;
        ArrayList<Double> aliquotas = new ArrayList<>();
        //anexo deve ser definido no banco de dados
        /*
         * Anexo I: Comércio (alíquotas variam de 4% a 11,61%)
         * Anexo II: Indústria (alíquotas variam de 4,5% a 12,11%)
         * Anexo III: Prestadores de serviços (alíquotas variam de 6% a 17,42%)
         * Anexo IV: Serviços como advocacia, medicina, entre outros (alíquotas variam de 4,5% a 16,85%)
         * Anexo V: Serviços de auditoria, jornalismo, publicidade, entre outros (alíquotas variam de 15,5% a 30,50%)
         */
        if (faturamento<=180000){
            imposto = faturamento * aliquotas.get(0);
        }else if (faturamento<=360000){
            imposto = faturamento * aliquotas.get(1);
        }else if (faturamento<=720000){
            imposto = faturamento * aliquotas.get(2);
        }else if (faturamento<=1800000){
            imposto = faturamento * aliquotas.get(3);
        }else if (faturamento<=3600000){
            imposto = faturamento * aliquotas.get(4);
        }else{
            return 0;
        }
        return imposto;
    }

}
