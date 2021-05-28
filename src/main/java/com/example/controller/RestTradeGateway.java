package com.example.controller;

import com.example.repository.Trade;
import com.example.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class RestTradeGateway {
    final TradeService tradeService;

    @GetMapping("/instrumentsBy")
    public void getInstrumentsBy(@RequestParam(required = false) Map<String, String> qParams) {//qparams@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("value") String value, @RequestParam("date") String date) {
        List<Trade> returnList = new ArrayList<>();
        TradeRequest trade = TradeRequest.builder()
                .clientId(qParams.getOrDefault("id", ""))
                .name(qParams.getOrDefault("name", ""))
                .price(Double.valueOf(qParams.getOrDefault("params", "0.0d")).doubleValue())
                //.date(qParams.getOrDefault("date"))
                .build();
        tradeService.post(trade);
        System.out.println("Hare Krishna");
        //returnList.add(TradeData);
        // return returnList;
    }
}
