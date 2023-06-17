package com.api.hackathon.Models;

import java.util.List;

public class Response { // MODELAGEM DE CLASSE PREPARADA PARA FORMATACAO FINAL DO ENVIO DO JSON
    private int codigoProduto;
    private String descricaoProduto;
    private double taxaJuros;
    private List<ParcelasSacPrice> resultadoSimulacao;

    public Response(int codigoProduto, String descricaoProduto, double taxaJuros, List<ParcelasSacPrice> resultadoSimulacao) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.taxaJuros = taxaJuros;
        this.resultadoSimulacao = resultadoSimulacao;
    }
    public Response(String error){
        this.descricaoProduto=error;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public List<ParcelasSacPrice> getResultadoSimulacao() {
        return resultadoSimulacao;
    }

    public void setResultadoSimulacao(List<ParcelasSacPrice> resultadoSimulacao) {
        this.resultadoSimulacao = resultadoSimulacao;
    }
}
