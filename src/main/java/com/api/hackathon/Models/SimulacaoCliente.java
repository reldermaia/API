package com.api.hackathon.Models;


import com.api.hackathon.Service.SimulacaoClienteController;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class SimulacaoCliente extends SimulacaoClienteController { // CLASSE MODELAR OS VALORES DIGITADOS PELO USUARIO
    @NotNull
    @Min(value = 1, message = "PRAZO NAO PODE SER NULO") // CONSTRAINT PARA CORRIGIR A TABELA QUE DEFINE O PRAZO MINIMO COMO 0, O QUE ESTÁ INCORRETO
    @Max(value = 32767, message = "PRAZO VALOR SUPERIOR AO PERMITIDO") //CONSTRAINT PARA EVITAR CONFLITO COM OS TAMANHO DO BANCO
    private Integer prazo;

    @NotNull
    @Max(value = Long.MAX_VALUE, message = "VALOR SUPERIOR AO PERMITIDO!")//CONSTRAINT PARA EVITAR CONFLITO COM OS TAMANHO DO BANCO
    private double valor;


    public SimulacaoCliente(Integer prazo, double valor) {

            this.prazo = prazo;
            this.valor = valor;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
