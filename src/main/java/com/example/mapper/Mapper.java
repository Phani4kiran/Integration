package com.example.mapper;

import com.example.controller.ClientPriceDetails;
import com.example.controller.InstrumentPriceDetails;
import com.example.endpoint.Trade;
import com.example.repository.Clients;
import com.example.repository.Instruments;

import java.time.LocalDate;

public class Mapper {
    public static InstrumentPriceDetails instrumentsToInstrumentPriceDetails(Instruments instruments) {
        return InstrumentPriceDetails.builder()
                .price(instruments.getInstrumentPrice())
                .instrumentCode(instruments.getInstrumentCode())
                .localDate(instruments.getInstrumentDate())
                .build();
    }

    public static ClientPriceDetails instrumentsToClientPriceDetails(Instruments instruments) {
        return ClientPriceDetails.builder()
                .price(instruments.getInstrumentPrice())
                .clientCode(instruments.getClients().getClientCode())
                .localDate(instruments.getInstrumentDate())
                .build();
    }

    public static Instruments tradeToInstrument(Clients clients, Trade trade) {
        return Instruments.builder()
                .instrumentCode(trade.getInstrumentCode())
                .instrumentPrice(trade.getInstrumentPrice())
                .clients(clients)
                .instrumentDate(LocalDate.now())
                .build();
    }
}
