package com.api.hackathon.Models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse { /* ESTA CLASSE É UM MODEL SIMPLES DE EXCECAO MAIS AMIGAVEL PARA O USUARIO, CASO
                                O FRONT END NAO CONFIGURE UMA PAGINA DE RESPOSTA DE ERRO OU QUEIRA APROVEITAR
                                A MENSAGEM SIMPLES QUE ESTA SENDO EXIBIDA NAS EXCECOES.
                                AQUI PODERIAMOS DEFINIR QUAISQUER DADOS SERIAM ENVIADOS EM CASO DE UMA EXCECAO
                                MAS ESCOLHI DEIXAR APENAS UM TIMESTAMP E UMA FRIENDLY MESSAGE*/

    private String message;

    private String timeStamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timeStamp= ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);


    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
