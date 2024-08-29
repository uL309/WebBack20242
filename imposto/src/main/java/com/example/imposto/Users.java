package com.example.imposto;

public interface Users {
    public String getNome();
    public String getEmail();
    public String getSenha();
    public String getRole();
    public double getFaturamento();
    public void setNome(String nome);
    public void setEmail(String email);
    public void setSenha(String senha);
    public void setFaturamento(double faturamento);

    public Boolean ICMS();
}
