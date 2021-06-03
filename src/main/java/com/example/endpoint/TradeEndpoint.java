package com.example.endpoint;

import com.example.controller.ClientPricesByInstrument;
import com.example.controller.InstrumentPricesByClient;
import com.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class TradeEndpoint {
    @Autowired
    private TradeService service;

    public Message<InstrumentPricesByClient> getInstrumentPricesByClient(Message<String> clientCodeMessage) {
        InstrumentPricesByClient instrumentPricesByClient = service.getInstrumentPriceByClientCode(clientCodeMessage.getPayload());
        return MessageBuilder.withPayload(instrumentPricesByClient).build();
    }

    public Message<ClientPricesByInstrument> getClientPricesByInstrumentCode(Message<String> instrumentCodeMessage) {
        ClientPricesByInstrument clientPricesByInstrumentCode = service.getClientPricesByInstrumentCode(instrumentCodeMessage.getPayload());
        return MessageBuilder.withPayload(clientPricesByInstrumentCode).build();
    }

    public void postTrades(Message<TradeMessage> msg) throws Exception {
        try {
            service.postTrades(msg.getPayload().getClientCode(), msg.getPayload().getData());

        } catch (Exception exception) {
            throw exception;
        }
    }
}
