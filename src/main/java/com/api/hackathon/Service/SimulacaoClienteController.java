package com.api.hackathon.Service;

import com.api.hackathon.Models.ProdutosBanco;
import com.api.hackathon.Models.SimulacaoCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


//CLASSE PARA CONTROLAR OS PARAMETROS DE ENTRADA DO USUARIO E FILTRAR O PRODUTO COM BASE NOS PRODUTOS CADASTRADOS NO BD
public class SimulacaoClienteController {
    Logger logger = LoggerFactory.getLogger(SimulacaoCliente.class);

    public boolean validarParametros(SimulacaoCliente dadosSimulacao, List<ProdutosBanco> produto) {
        /* STATEMENT PARA VALIDAR SE OS VALORES DIGITADOS SAO ABAIXO DO MINIMO CADASTRADOS NO BANCO
         LEMBRANDO QUE NAO HA VALORES MAXIMOS PRA PRAZO OU VALOR
         ESSE STATEMENT DEPENDE DA ORGANIZACAO CRESCENTE DO BD. COMO DE FATO ESTÁ ORGANIZADO */
        logger.info("VALIDANDO PARAMETROS DO USUARIO...");
                if (dadosSimulacao.getValor() < produto.get(0).getVr_minimo() || dadosSimulacao.getPrazo() < produto.get(0).getNu_minimo_meses()) {
                    logger.warn("PARAMETROS INVALIDADOS!!!");
                    return false;
                } else {
                    logger.info("PARAMETROS VALIDADOS");
                    return true;
                }
    }
    public ProdutosBanco filtrarProduto(List<ProdutosBanco> produto, Integer prazo, double valor) { // METODO DE FILTRAGEM DOS PRODUTOS COM BASE NOS PARAMETROS DO BANCO DE DADOS
        ProdutosBanco produtoFiltrado = new ProdutosBanco();
        for (int i=0; i<produto.size()-1;i++) {
            if ((prazo >= produto.get(i).getNu_minimo_meses() && prazo <= produto.get(i).getNu_maximo_meses() && valor >= produto.get(i).getVr_minimo() && valor <= produto.get(i).getVr_maximo())) {
                return produtoFiltrado = produto.get(i);
            } else if ((prazo>produto.get(produto.size()-2).getNu_maximo_meses() && valor>produto.get(produto.size()-2).getVr_maximo())){
                return produtoFiltrado=produto.get(produto.size()-1);
            }
        }
        return produtoFiltrado;
    }
}
