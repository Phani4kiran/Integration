package com.example.controller;

import com.example.repository.TradeData;
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
    //    TradeService tradeService;
    @GetMapping("/instrumentsBy")
    public void getInstrumentsBy(@RequestParam(required=false)Map<String,String> qParams){//qparams@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("value") String value, @RequestParam("date") String date) {
        List<TradeData> returnList = new ArrayList<>();

        System.out.println("Hare Krishna");
        returnList.add(new TradeData());
        // return returnList;
    }
}
