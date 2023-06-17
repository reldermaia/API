package com.api.hackathon.Service;


import com.api.hackathon.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
public class PostController implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... args) throws Exception {
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    /*AQUI PODERIAMOS CONFIGURAR OS HEADERS NECESSARIOS, MAS PARA O CONTEXTO DO DESAFIO NAO É NECESSARIO
    CLASSE QUE CONSULTA O BD E CONTROLA A REQUISICAO DO USUARIO
    NO CONTEXTO DO DESAFIO NAO É NECESSARIA A IMPLEMENTACAO DOS DEMAIS VERBOS HTTP, PORTANTO FOI IMPLEMENTADO SOMENTE POST*/

    public ResponseEntity<?> postController(@Validated @RequestBody SimulacaoCliente simulacaoCliente) throws JsonProcessingException, DataAccessException {
        logger.info("INICIANDO CONSULTA AO BANCO DE DADOS...");
        List<ProdutosBanco> todosProdutos = jdbcTemplate.query("SELECT * FROM PRODUTO", BeanPropertyRowMapper.newInstance(ProdutosBanco.class));
        logger.info("CONSULTA EFETUADA COM SUCESSO!!!");
        ResponseController respostaFormatada = new ResponseController();
        return ResponseEntity.status(201).body(respostaFormatada.formatarResposta(simulacaoCliente, todosProdutos));
    }
}



