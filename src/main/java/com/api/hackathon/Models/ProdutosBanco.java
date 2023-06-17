package com.api.hackathon.Models;


public class ProdutosBanco { // MODELAGEM DA CLASSE DE PRODUTOS DO BANCO DE DADOS
    private int co_produto;
    private String no_produto;
    private double pc_taxa_juros;
    private int nu_minimo_meses;
    private Integer nu_maximo_meses;
    private double vr_minimo;
    private Double vr_maximo;


    public int getCo_produto() {
        return co_produto;
    }

    public void setCo_produto(int co_produto) {
        this.co_produto = co_produto;
    }

    public String getNo_produto() {
        return no_produto;
    }

    public void setNo_produto(String no_produto) {
        this.no_produto = no_produto;
    }

    public double getPc_taxa_juros() {
        return pc_taxa_juros;
    }

    public void setPc_taxa_juros(double pc_taxa_juros) {
        this.pc_taxa_juros = pc_taxa_juros;
    }

    public int getNu_minimo_meses() {
        return nu_minimo_meses;
    }

    public void setNu_minimo_meses(int nu_minimo_meses) {
        this.nu_minimo_meses = nu_minimo_meses;
    }

    public Integer getNu_maximo_meses() {
        return nu_maximo_meses;
    }

    public void setNu_maximo_meses(Integer nu_maximo_meses) {
        this.nu_maximo_meses = nu_maximo_meses;
    }

    public double getVr_minimo() {
        return vr_minimo;
    }

    public void setVr_minimo(double vr_minimo) {
        this.vr_minimo = vr_minimo;
    }

    public Double getVr_maximo() {
        return vr_maximo;
    }

    public void setVr_maximo(Double vr_maximo) {
        this.vr_maximo = vr_maximo;
    }

}
