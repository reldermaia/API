package com.api.hackathon.Models;

import java.util.List;

public class ParcelasSacPrice { /* CLASSE PARA FORMATACAO DO ENVIO DO ARQUIVO JSON
    VISTO QUE A RESPOSTA DEVE CONTER UM ARRAY COM 2 OBJETOS, AS PARCELAS SAC E PRICE*/
    private String tipo;
    private List<Parcela> parcelas;

    public ParcelasSacPrice(String tipo, List<Parcela> parcelas) {
        this.tipo = tipo;
        this.parcelas = parcelas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
}
