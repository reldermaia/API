package com.api.hackathon.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventData extends com.azure.messaging.eventhubs.EventData {
    /* ESTA CLASSE VAI FORMATAR A RESPOSTA PARA CORRETO ENVIO AO EVENTHUB, VISTO
    QUE NAO PODEMOS MANDAR UM JSON DIRETAMENTE PARA LA.
     */
    Logger logger = LoggerFactory.getLogger(EventData.class);
    private String res;

    public EventData(Response res) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(res);
        logger.info(json); // PASSANDO O RETORNO PARA O LOG PARA CONFERENCIA DA INTEGRIDADE DA RESPOSTA JSON ENVIADA AO EVENTHUB COM A RESPOSTA HTTP
        this.res = json;
    }
}
