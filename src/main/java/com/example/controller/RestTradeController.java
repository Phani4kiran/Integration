package com.example.controller;

import com.example.endpoint.Trade;
import com.example.endpoint.TradeMessage;
import com.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class RestTradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping(path = "instruments/{clientCode}/prices")
    public ResponseEntity<InstrumentPricesByClient> getInstrumentPriceByClientCode(@PathVariable String clientCode) {
        final InstrumentPricesByClient instrumentPriceByClientCode = tradeService.getInstrumentPriceByClientCode(clientCode);
        return ResponseEntity.status(OK).body(instrumentPriceByClientCode);
    }

    @GetMapping(path = "vendors/{instrumentCode}/prices")
    public ResponseEntity<ClientPricesByInstrument> getClientPricesByInstrumentCode(@PathVariable String instrumentCode) {
        final ClientPricesByInstrument instrumentPriceByClientCode = tradeService.getClientPricesByInstrumentCode(instrumentCode);
        if (instrumentPriceByClientCode == null) {
            ResponseEntity.status(BAD_REQUEST);
        }
        return ResponseEntity.status(OK).body(instrumentPriceByClientCode);
    }

    @PostMapping(path = "client/{clientCode}/trades")
    public ResponseEntity<List<Trade>> postTrades(@PathVariable String clientCode, @RequestBody TradeMessage tradeMessage) {
        try {
            tradeService.postTrades(clientCode, tradeMessage.getData());
        } catch (Exception ex) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        return ResponseEntity.status(CREATED).build();
    }
}
