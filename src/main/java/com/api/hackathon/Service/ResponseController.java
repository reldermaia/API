package com.api.hackathon.Service;

import com.api.hackathon.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static com.api.hackathon.Messaging.Sender.publishEvents;


//CLASSE PARA CONTROLE DA FORMATACAO DA RESPOSTA DA REQUISICAO POST DE POSTCONTROLLER
public class ResponseController {

    Logger logger = LoggerFactory.getLogger(ResponseController.class);

    public Response formatarResposta(SimulacaoCliente simulacaoCliente, List<ProdutosBanco> todosProdutos) throws JsonProcessingException {
        if (simulacaoCliente.validarParametros(simulacaoCliente, todosProdutos)) {
            ProdutosBanco produtoFiltrado = (simulacaoCliente.filtrarProduto(todosProdutos, simulacaoCliente.getPrazo(), simulacaoCliente.getValor()));
            // AQUI JÁ TEMOS O PRODUTO CORRESPONDENTE AOS PARAMETROS INSERIDOS PELO USUARIO
            if (produtoFiltrado.getNo_produto()==null){
                throw new NoSuchElementException("NENHUM PRODUTO ENCONTRADO PARA OS VALORES DE ENTRADA");
            }
            Parcela parcela = new Parcela();
            List<Parcela> listaParcelasSac = new ArrayList<>(parcela.calcularParcelaSac(simulacaoCliente, produtoFiltrado));
            List<Parcela> listaParcelasPrice = new ArrayList<>(parcela.calcularParcelaPrice(simulacaoCliente, produtoFiltrado));
            ParcelasSacPrice sac = new ParcelasSacPrice("SAC", listaParcelasSac);
            ParcelasSacPrice price = new ParcelasSacPrice("PRICE", listaParcelasPrice);
            List<ParcelasSacPrice> parcelasFormatadas = new ArrayList<>();
            parcelasFormatadas.add(sac);
            parcelasFormatadas.add(price);
            Response res = new Response(produtoFiltrado.getCo_produto(), produtoFiltrado.getNo_produto(), produtoFiltrado.getPc_taxa_juros(), parcelasFormatadas);
            logger.info("INICIANDO ENVIO DO OBJETO JSON PARA O EVENTHUB");
            publishEvents(res); // AQUI É ENVIADO O MESMO ENVELOPE JSON PARA O EVENTHUB UTILIZANDO A CLASSE SENDER NO PACOTE MESSAGING
            logger.info("OBJETO ENVIADO COM SUCESSO");
            return res;
        } else throw new IllegalArgumentException("VALOR ABAIXO DOS VALORES MINIMOS!");
    }
}
