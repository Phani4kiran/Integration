package com.example.service;

import com.example.controller.ClientPriceDetails;
import com.example.controller.ClientPricesByInstrument;
import com.example.controller.InstrumentPriceDetails;
import com.example.controller.InstrumentPricesByClient;
import com.example.endpoint.Trade;
import com.example.mapper.Mapper;
import com.example.repository.Clients;
import com.example.repository.ClientsRepository;
import com.example.repository.Instruments;
import com.example.repository.InstrumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private InstrumentsRepository instrumentsRepository;

    public void postTrades(String clientCode, List<Trade> trades) throws Exception {
        if (clientCode == null || trades == null) {
            throw new Exception();
        }
        Clients clientsDb = clientsRepository.findByClientCode(clientCode);
        if (clientsDb == null) {
            Clients clients = Clients.builder().clientCode(clientCode).build();
            clientsRepository.saveAndFlush(clients);
        }
        final Clients finalClientsDb = clientsRepository.findByClientCode(clientCode);
        List<Instruments> instruments = trades.stream().map(o -> Mapper.tradeToInstrument(finalClientsDb, o)).collect(Collectors.toList());
        instrumentsRepository.saveAllAndFlush(instruments);
    }

    public InstrumentPricesByClient getInstrumentPriceByClientCode(String clientCode) {
        final Clients clients = clientsRepository.findByClientCode(clientCode);
        final List<Instruments> instrumentsList = instrumentsRepository.findByClients(clients);
        List<InstrumentPriceDetails> instrumentPriceDetailsList = instrumentsList.stream()
                .map(Mapper::instrumentsToInstrumentPriceDetails)
                .collect(Collectors.toList());
        return InstrumentPricesByClient.builder()
                .clientCode(clientCode)
                .instrumentPriceDetails(instrumentPriceDetailsList)
                .build();

    }

    public ClientPricesByInstrument getClientPricesByInstrumentCode(String instrumentCode) {
        final List<Instruments> instrumentList = instrumentsRepository.findByInstrumentCode(instrumentCode);
        List<ClientPriceDetails> clientPriceDetailsList = instrumentList.stream()
                .map(Mapper::instrumentsToClientPriceDetails)
                .collect(Collectors.toList());
        return ClientPricesByInstrument.builder()
                .instrumentCode(instrumentCode)
                .clientPriceDetails(clientPriceDetailsList)
                .build();

    }
}
