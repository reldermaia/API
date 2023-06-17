package com.api.hackathon.Messaging;

import com.api.hackathon.Models.EventData;
import com.api.hackathon.Models.Response;
import com.azure.messaging.eventhubs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Sender { // CLASSE PARA ENVIO DO EVENTO AO EVENTHUB

    private static final String connectionString = "Endpoint=sb://eventhack.servicebus.windows.net/;SharedAccessKeyName=hack;SharedAccessKey=HeHeVaVqyVkntO2FnjQcs2Ilh/4MUDo4y+AEhKp8z+g=;EntityPath=simulacoes";


    public static void publishEvents(Response res) throws JsonProcessingException {


            EventHubProducerClient producer = new EventHubClientBuilder()
                    .connectionString(connectionString)
                    .buildProducerClient();


        List<com.api.hackathon.Models.EventData> allEvents = Arrays.asList(new com.api.hackathon.Models.EventData(res));

        /* AQUI ESTA SENDO PASSADA A RESPOSTA HTTP, MAS O CONTRUTOR EVENTDATA EM MODELS CERTIICA-SE
        DE TRANSFORMAAR A RESPOSTA HTTP EM JSON PARA CORRETO ENVIO CONFORME DEFINICOES
        DO DESAFIO. */

        EventDataBatch eventDataBatch = producer.createBatch();

        for (EventData eventData : allEvents) {
            if (!eventDataBatch.tryAdd(eventData)) {
                producer.send(eventDataBatch);
                eventDataBatch = producer.createBatch();
                if (!eventDataBatch.tryAdd(eventData)) {
                    throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
                            + eventDataBatch.getMaxSizeInBytes());
                }
            }
        }
        if (eventDataBatch.getCount() > 0) {
            producer.send(eventDataBatch);
        }
        producer.close();
    }
}