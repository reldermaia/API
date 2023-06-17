package com.api.hackathon.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Parcela { /* MODELAGEM DA CLASSE DAS PARCELAS QUE SERAO EXIBIDAS NA RESPOSTA
                            JUNTAMENTE COM OS METODOS DE CALCULO DAS PARCELAS SAC E PRICE */
    private int numero;
    private double valorAmortizacao;
    private double valorJuros;
    private double valorPrestacao;

    public List<Parcela> calcularParcelaSac(SimulacaoCliente valorSimulado, ProdutosBanco produtoFiltrado){
        List<Parcela> listaParcelas = new ArrayList<>();
        double taxa = produtoFiltrado.getPc_taxa_juros();
        double valor = valorSimulado.getValor();
        int prazo = valorSimulado.getPrazo();
        double amortizacao = valor/prazo;
        for(int i=0; i<valorSimulado.getPrazo(); i++){
            Parcela parcela = new Parcela();
            parcela.setValorAmortizacao(amortizacao);
            parcela.setNumero(i+1);
            parcela.setValorJuros(valor*taxa);
            parcela.setValorPrestacao(parcela.getValorAmortizacao()+ parcela.getValorJuros());
            valor-= amortizacao;
            listaParcelas.add(parcela);
        }
        return listaParcelas;
    }
    public List<Parcela> calcularParcelaPrice(SimulacaoCliente valorSimulado, ProdutosBanco produtoFiltrado){
        List<Parcela> listaParcelas = new ArrayList<>();
        double taxa = produtoFiltrado.getPc_taxa_juros();
        double valor = valorSimulado.getValor();
        int prazo = valorSimulado.getPrazo();
        double prestacao = ((valor*taxa)/(1-Math.pow(1+taxa, -prazo)));
        for(int i=0; i<valorSimulado.getPrazo(); i++){
            Parcela parcela = new Parcela();
            parcela.setValorPrestacao(prestacao);
            parcela.setNumero(i+1);
            parcela.setValorJuros(valor*taxa);
            parcela.setValorAmortizacao(parcela.getValorPrestacao()-parcela.getValorJuros());
            valor-= parcela.getValorAmortizacao();
            listaParcelas.add(parcela);
        }
        return listaParcelas;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValorAmortizacao() {
        return valorAmortizacao;
    }

    public void setValorAmortizacao(double valorAmortizacao) {
        this.valorAmortizacao = formatarDecimais(valorAmortizacao);
    }

    public double getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(double valorJuros) {
        this.valorJuros = formatarDecimais(valorJuros);
    }

    public double getValorPrestacao() {
        return valorPrestacao;
    }

    public void setValorPrestacao(double valorPrestacao) {
        this.valorPrestacao = formatarDecimais(valorPrestacao);
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "numero=" + numero +
                ", valorAmortizacao=" + valorAmortizacao +
                ", valorJuros=" + valorJuros +
                ", valorPrestacao=" + valorPrestacao +
                '}';
    }
    public static double formatarDecimais (double valor){
        BigDecimal bigDecimal = new BigDecimal(valor);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        return bigDecimal.doubleValue();

    }
};

